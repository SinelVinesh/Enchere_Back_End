package mg.cloud.enchere_back_end.Service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import mg.cloud.enchere_back_end.Model.*;
import mg.cloud.enchere_back_end.Repository.AppUserFullBalanceRepository;
import mg.cloud.enchere_back_end.Repository.AppUserRepository;
import mg.cloud.enchere_back_end.Repository.AppUserTokenRepository;
import mg.cloud.enchere_back_end.Repository.UserPhotoRepository;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import mg.cloud.enchere_back_end.request.AppUserInput;
import mg.cloud.enchere_back_end.request.AppUserUpdateInput;
import mg.cloud.enchere_back_end.request.Photo;
import mg.cloud.enchere_back_end.response.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppUserFullBalanceRepository appUserFullBalanceRepository;
    @Autowired
    private UserPhotoRepository userPhotoRepository;
    @Autowired
    private AppUserTokenRepository appUserTokenRepository;
    @Autowired
    private UserPhotoRepository photoRepository;
    @Autowired
    private Storage storage;

    private final AppUserTokenService appUserTokenService;

    public AppUserService(AppUserTokenService appUserTokenService) {
        this.appUserTokenService = appUserTokenService;
    }



    public AppUserToken login(AppUser appUser) throws InvalidValueException {
        Optional<AppUser> data = appUserRepository.findByUsernameOrEmail(appUser.getUsername(),appUser.getEmail());
        if(data.isPresent()) {
            if(!data.get().getPassword().equals(DigestUtils.sha1Hex(appUser.getPassword()))) {
                throw new InvalidValueException("L'authentification a échoué, veuillez vérifier vos identifiants");
            }
            return data.map(appUserTokenService::generateToken).orElse(null);
        } else {
            throw new InvalidValueException("L'authentification a échoué, veuillez vérifier vos identifiants");
        }
    }

    public AppUser saveAppUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public AppUser findById(Long id){
        return appUserRepository.findById(id).get();
    }

    public boolean logout(String token) throws InvalidValueException {
        return appUserTokenService.removeToken(token);
    }

    public AppUserToken register(AppUserInput user) throws InvalidValueException {
        if(user.getUsername() == null || user.getPassword() == null || user.getEmail() == null || user.getBirthDate() == null) {
            throw new InvalidValueException("Veuillez remplir tous les champs (genre, nom d'utilisateur, mot de passe, email, date de naissance)");
        }
        Optional<AppUser> usernameOrEmail = appUserRepository.findByUsernameOrEmail(user.getUsername(),user.getEmail());
        if(usernameOrEmail.isPresent()) {
            String error = "";
            if(usernameOrEmail.get().getUsername().equals(user.getUsername()))
                error = "Le nom d'utilisateur est déjà utilisé";
            if(usernameOrEmail.get().getEmail().equals(user.getEmail()))
                error = "L'adresse email est déjà utilisée";
            throw new InvalidValueException(error);
        }
        AppUser toSave = new AppUser();
        toSave.setUsername(user.getUsername());
        toSave.setPassword(user.getPassword());
        toSave.setEmail(user.getEmail());
        toSave.setBirthDate(user.getBirthDate());
        toSave.setRegistrationDate(LocalDate.now());

        AppUser appUser = saveAppUser(toSave);
        return appUserTokenService.generateToken(appUser);
    }

    public ResponseEntity<Response> getUser(Long id) throws InvalidValueException {
        AppUserFullBalance user = appUserFullBalanceRepository.findById(id).orElseThrow(() -> new InvalidValueException("L'utilisateur n'existe pas"));
        UserPhoto userPhoto = userPhotoRepository.findByUserId(id).orElse(null);
        AppUserWithPhoto appUserWithPhoto = new AppUserWithPhoto();
        appUserWithPhoto.setId(user.getId());
        appUserWithPhoto.setEmail(user.getEmail());
        appUserWithPhoto.setUsername(user.getUsername());
        appUserWithPhoto.setBirthDate(user.getBirthDate());
        appUserWithPhoto.setRegistrationDate(user.getRegistrationDate());
        appUserWithPhoto.setPhoto(userPhoto);
        appUserWithPhoto.setBalance(user.getBalance());
        appUserWithPhoto.setUsableBalance(user.getUsableBalance());
        return ResponseEntity.ok(new Response(appUserWithPhoto));
    }

    public ResponseEntity<Response> updateUser(AppUserUpdateInput appUser, String token, Long id) throws InvalidValueException {
        AppUserToken appUserToken = appUserTokenRepository.findByValue(token.split(" ")[1]).orElseThrow(() -> new InvalidValueException("Le token est invalide"));
        AppUser user = appUserRepository.findById(appUserToken.getUser().getId()).orElseThrow(() -> new InvalidValueException("L'utilisateur n'existe pas"));
        if(!user.getId().equals(id)) {
            throw new InvalidValueException("Vous n'avez pas les droits pour modifier cet utilisateur");
        }
        if(appUser.getUsername() != null) {
            user.setUsername(appUser.getUsername());
        }
        if(appUser.getEmail() != null) {
            user.setEmail(appUser.getEmail());
        }
        if(appUser.getPassword() != null) {
            user.setPassword(appUser.getPassword());
        }
        AppUser userResult = appUserRepository.save(user);
        AppUserFullBalance appUserFullBalance = appUserFullBalanceRepository.findById(userResult.getId()).orElseThrow(() -> new InvalidValueException("L'utilisateur n'existe pas"));
        AppUserWithPhoto appUserWithPhoto = new AppUserWithPhoto();
        appUserWithPhoto.setId(appUserFullBalance.getId());
        appUserWithPhoto.setEmail(appUserFullBalance.getEmail());
        appUserWithPhoto.setUsername(appUserFullBalance.getUsername());
        appUserWithPhoto.setBirthDate(appUserFullBalance.getBirthDate());
        appUserWithPhoto.setRegistrationDate(appUserFullBalance.getRegistrationDate());
        appUserWithPhoto.setBalance(appUserFullBalance.getBalance());
        appUserWithPhoto.setUsableBalance(appUserFullBalance.getUsableBalance());
        if(appUser.getPhoto() != null) {
            String path = savePhoto(appUser.getPhoto());
            Optional<UserPhoto> userPhoto = userPhotoRepository.findByUserId(user.getId());
            UserPhoto photo = null;
            if(userPhoto.isPresent()) {
                photo = userPhoto.get();
                photo.setPhotoPath(path);
            } else {
                photo = new UserPhoto();
                photo.setPhotoPath(path);
                photo.setUserId(user.getId());
            }
            photoRepository.save(photo);
            appUserWithPhoto.setPhoto(photo);
        }
        return ResponseEntity.ok(new Response(appUserWithPhoto));
    }

    private String savePhoto(Photo photo) throws InvalidValueException {
        byte[] decoded = Base64.getDecoder().decode(photo.getBase64String());
        Tika tika = new Tika();
        String mimeType = tika.detect(decoded).split("/")[0];
        if(!mimeType.equals("image")){
            throw new InvalidValueException("Le fichier envoyé n'est pas une image");
        }
        String fileName = "/images/" + DigestUtils.sha1Hex(LocalDateTime.now().toString()) + "." + photo.getFormat();
        BlobId blobId = BlobId.of("enchereapp",fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/"+photo.getFormat()).build();
        Blob blob = storage.create(blobInfo, decoded);
        System.out.println(blob.getMediaLink());
        return blob.getMediaLink();
    }
}
