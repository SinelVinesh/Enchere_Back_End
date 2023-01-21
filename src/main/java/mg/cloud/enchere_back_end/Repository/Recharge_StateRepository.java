package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.ReloadState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Recharge_StateRepository extends JpaRepository<ReloadState,Long> {
    Optional<ReloadState> findById(Long id);
}
