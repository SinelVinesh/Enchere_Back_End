package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.Admin;
import mg.cloud.enchere_back_end.Model.AdminToken;
import mg.cloud.enchere_back_end.Model.SettingsValueHistory;
import mg.cloud.enchere_back_end.Repository.AdminTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Timestamp;
import java.time.Instant;


@Service
public class AdminTokenService {
    @Autowired
    private AdminTokenRepository adminTokenRepository;
    @Autowired
    private SettingsService settingsService;

    public AdminToken generateToken(Admin admin) {
            SettingsValueHistory tokenDuration = settingsService.getCurrentValue(1L);
            Timestamp expiration = Timestamp.from(Instant.now().plusSeconds(Integer.parseInt(tokenDuration.getValue())));
            String tokenString = admin.getPassword() + admin.getUsername() + expiration;
            String hash = DigestUtils.sha1Hex(tokenString);
            adminTokenRepository.deleteAllByAdminId(admin.getId());
            AdminToken token = new AdminToken();
            token.setAdmin(admin);
            token.setValue(hash);
            token.setExpirationDate(expiration);
            token.setCreation_date(Timestamp.from(Instant.now()));
            adminTokenRepository.save(token);
            return adminTokenRepository.save(token);
    }

    public boolean authenticate(String token) {
        AdminToken data = adminTokenRepository.findByValue(token).orElse(null);
        return data != null && data.getExpirationDate().after(Timestamp.from(Instant.now()));
    }

    public boolean removeToken(String token) {
        try {
            adminTokenRepository.deleteByValue(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
