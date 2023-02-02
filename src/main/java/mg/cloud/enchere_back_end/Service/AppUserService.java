package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.AppUser;
import mg.cloud.enchere_back_end.Model.AppUserToken;
import mg.cloud.enchere_back_end.Repository.AppUserRepository;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import mg.cloud.enchere_back_end.inputs.AppUserInput;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

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
        AppUser toSave = new AppUser(null,user.getUsername(),user.getPassword(),user.getEmail(),user.getBirthDate(),LocalDate.now(),0);

        AppUser appUser = saveAppUser(toSave);
        return appUserTokenService.generateToken(appUser);
    }
}
