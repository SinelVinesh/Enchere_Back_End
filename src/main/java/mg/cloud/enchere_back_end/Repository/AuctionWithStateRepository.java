package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.AuctionWithState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionWithStateRepository extends JpaRepository<AuctionWithState, Long> {
}
