package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByUsernameOrEmail(String username,String email);
}
