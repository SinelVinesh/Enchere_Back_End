package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.Admin;
import mg.cloud.enchere_back_end.Model.AdminToken;
import mg.cloud.enchere_back_end.Repository.AdminTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;


@Service
public class AdminTokenService {
    @Autowired
    private AdminTokenRepository adminTokenRepository;

    public AdminToken generateToken(Admin admin) {
            long duration = 3600;
            LocalDateTime expiration = LocalDateTime.now().plusSeconds(duration);
            String tokenString = admin.getPassword() + admin.getUsername() + expiration;
            String hash = DigestUtils.sha1Hex(tokenString);
            adminTokenRepository.deleteAllByAdminId(admin.getId());
            AdminToken token = new AdminToken();
            token.setAdmin(admin);
            token.setValue(hash);
            token.setExpirationDate(expiration);
            token.setCreation_date(LocalDateTime.now());
            adminTokenRepository.save(token);
            return adminTokenRepository.save(token);
    }

    public boolean authenticate(String token) {
        AdminToken data = adminTokenRepository.findByValue(token).orElse(null);
        if(data != null && data.getExpirationDate().isAfter(LocalDateTime.now())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removeToken(String token) {
        try {
            Long deleted = adminTokenRepository.deleteByValue(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
