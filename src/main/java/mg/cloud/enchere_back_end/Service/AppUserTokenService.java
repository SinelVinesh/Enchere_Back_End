package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.AppUser;
import mg.cloud.enchere_back_end.Model.AppUserToken;
import mg.cloud.enchere_back_end.Repository.AppUserTokenRepository;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class AppUserTokenService {
    @Autowired
    private AppUserTokenRepository appUserTokenRepository;

    public AppUserToken generateToken(AppUser app_user) {
        long duration = 3600;
        LocalDateTime expiration = LocalDateTime.now().plusSeconds(duration);
        String tokenString = app_user.getPassword() + app_user.getUsername() + expiration;
        String hash = DigestUtils.sha1Hex(tokenString);
        appUserTokenRepository.deleteAllByUserId(app_user.getId());
        AppUserToken token = new AppUserToken();
        token.setUser(app_user);
        token.setValue(hash);
        token.setExpirationDate(expiration);
        token.setCreationDate(LocalDateTime.now());
        appUserTokenRepository.save(token);
        return token;
    }
    public boolean removeToken(String token) throws InvalidValueException {
        try {
            appUserTokenRepository.deleteByValue(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidValueException("La déconnexion a échoué");
        }
    }
    public boolean authenticate(String token) throws InvalidValueException {
        AppUserToken data = appUserTokenRepository.findByValue(token).orElse(null);
        if(data == null) {
            throw  new InvalidValueException("L'authentification a échoué");
        }
        if(data.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw  new InvalidValueException("Votre session a expiré");
        }
        return true;
    }
    public AppUserToken findByValue(String token){
        return appUserTokenRepository.findByValue(token).orElse(null);
    }
}
