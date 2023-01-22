package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.Admin;
import mg.cloud.enchere_back_end.Model.Admin_token;
import mg.cloud.enchere_back_end.Repository.Admin_tokenRepository;
import mg.cloud.enchere_back_end.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class Admin_tokenService {
    @Autowired
    private Admin_tokenRepository admin_tokenRepository;

    public String generateToken(Admin admin) {
            long duration = 3600;
            LocalDateTime expiration = LocalDateTime.now().plusSeconds(duration);
            String tokenString = admin.getPassword() + admin.getUsername() + expiration;
            String hash = DigestUtils.sha1Hex(tokenString);
            admin_tokenRepository.deleteAllByAdminId(admin.getId());
            Admin_token token = new Admin_token();
            token.setAdmin(admin);
            token.setValue(hash);
            token.setExpiration_date(expiration);
            token.setCreation_date(LocalDateTime.now());
            admin_tokenRepository.save(token);
            return hash;
    }

    public boolean authenticate(String token) {
        Admin_token data = admin_tokenRepository.findByValue(token).orElse(null);
        if(data != null && data.getExpiration_date().isAfter(LocalDateTime.now())) {
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
