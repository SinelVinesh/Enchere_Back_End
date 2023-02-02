package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUsernameOrEmail(String username, String email);

    @Override
    Optional<AppUser> findById(Long id);

}
