package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.Admin;
import mg.cloud.enchere_back_end.Model.Admin_token;
import mg.cloud.enchere_back_end.Model.App_user;
import mg.cloud.enchere_back_end.Model.App_user_token;
import mg.cloud.enchere_back_end.Repository.Admin_tokenRepository;
import mg.cloud.enchere_back_end.Repository.App_user_tokenRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class App_user_tokenService {
    @Autowired
    private App_user_tokenRepository app_user_tokenRepository;

    public String generateToken(App_user app_user) {
        long duration = 3600;
        Timestamp expiration = Timestamp.valueOf(LocalDateTime.now().plusSeconds(duration));
        String tokenString = app_user.getPassword() + app_user.getUsername() + expiration;
        String hash = DigestUtils.sha1Hex(tokenString);
        app_user_tokenRepository.deleteAllByUserId(app_user.getId());
        App_user_token token = new App_user_token();
        token.setUser(app_user);
        token.setValue(hash);
        token.setExpiration_date(expiration);
        token.setCreation_date(Timestamp.valueOf(LocalDateTime.now()));
        app_user_tokenRepository.save(token);
        return hash;
    }
}
