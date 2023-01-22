package mg.cloud.enchere_back_end.Repository;

import jakarta.transaction.Transactional;
import mg.cloud.enchere_back_end.Model.Admin_token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Admin_tokenRepository extends JpaRepository<Admin_token,Long> {
    Optional<Admin_token> findByValue(String token);

    @Transactional
    void deleteAllByAdminId(Long AdminId);

    @Transactional
    Long deleteByValue(String token);
}
