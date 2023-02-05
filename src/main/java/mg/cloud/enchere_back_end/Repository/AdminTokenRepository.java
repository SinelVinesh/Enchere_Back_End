package mg.cloud.enchere_back_end.Repository;

import jakarta.transaction.Transactional;
import mg.cloud.enchere_back_end.Model.AdminToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminTokenRepository extends JpaRepository<AdminToken,Long> {
    Optional<AdminToken> findByValue(String token);

    @Transactional
    void deleteAllByAdminId(Long AdminId);

    @Transactional
    Long deleteByValue(String token);
}
