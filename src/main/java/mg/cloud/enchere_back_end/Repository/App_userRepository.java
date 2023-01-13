package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.App_user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface App_userRepository extends JpaRepository<App_user,Long> {
    Optional<App_user> findByUsernameOrEmailAndPassword(String username, String email, String password);
}
