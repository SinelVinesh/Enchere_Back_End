package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.App_user;
import mg.cloud.enchere_back_end.Model.Recharge_state;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Recharge_StateRepository extends JpaRepository<Recharge_state,Long> {
    Optional<Recharge_state> findById(Long id);
}
