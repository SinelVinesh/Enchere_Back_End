package mg.cloud.enchere_back_end.Repository;

import jakarta.transaction.Transactional;
import mg.cloud.enchere_back_end.Model.AppUserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserTokenRepository extends JpaRepository<AppUserToken,Long> {
    Optional<AppUserToken> findByValue(String token);

    @Transactional
    void deleteAllByUserId(Long App_userId);

    @Transactional
    void deleteByValue(String token);
}
