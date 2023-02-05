package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.AuctionStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionStatRepository extends JpaRepository<AuctionStat, Long> {
}
