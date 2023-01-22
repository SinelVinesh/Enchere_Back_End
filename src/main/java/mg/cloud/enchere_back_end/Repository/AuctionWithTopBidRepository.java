package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.AuctionWithTopBid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionWithTopBidRepository extends JpaRepository<AuctionWithTopBid, Long> {
    AuctionWithTopBid findFirstByOrderByTopBidAmountAsc();
    AuctionWithTopBid findFirstByOrderByTopBidAmountDesc();
}
