package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.AuctionState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuctionStateRepository extends JpaRepository<AuctionState,Long> {
    Optional<AuctionState> findById(Long id);
}
