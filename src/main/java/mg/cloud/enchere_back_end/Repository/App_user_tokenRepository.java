package mg.cloud.enchere_back_end.Repository;

import jakarta.transaction.Transactional;
import mg.cloud.enchere_back_end.Model.Admin_token;
import mg.cloud.enchere_back_end.Model.App_user_token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface App_user_tokenRepository  extends JpaRepository<App_user_token,Long> {
    Optional<App_user_token> findByValue(String token);

    @Transactional
    void deleteAllByUserId(Long App_userId);
}
