package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.ReloadRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReloadRequestRepository extends JpaRepository<ReloadRequest,Long> {
    @Override
    Optional<ReloadRequest> findById(Long aLong);
}
