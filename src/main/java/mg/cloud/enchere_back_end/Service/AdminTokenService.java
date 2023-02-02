package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.Admin;
import mg.cloud.enchere_back_end.Model.Admin_token;
import mg.cloud.enchere_back_end.Repository.AdminTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;


@Service
public class AdminTokenService {
    @Autowired
    private AdminTokenRepository admin_tokenRepository;

    public String generateToken(Admin admin) {
            long duration = 3600;
            LocalDateTime expiration = LocalDateTime.now().plusSeconds(duration);
            String tokenString = admin.getPassword() + admin.getUsername() + expiration;
            String hash = DigestUtils.sha1Hex(tokenString);
            admin_tokenRepository.deleteAllByAdminId(admin.getId());
            Admin_token token = new Admin_token();
            token.setAdmin(admin);
            token.setValue(hash);
            token.setExpirationDate(expiration);
            token.setCreation_date(LocalDateTime.now());
            admin_tokenRepository.save(token);
            return hash;
    }

    public boolean authenticate(String token) {
        Admin_token data = admin_tokenRepository.findByValue(token).orElse(null);
        if(data != null && data.getExpirationDate().isAfter(LocalDateTime.now())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removeToken(String token) {
        try {
            Long deleted = admin_tokenRepository.deleteByValue(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
