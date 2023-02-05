package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.Auction;
import mg.cloud.enchere_back_end.Model.AuctionWithState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface AuctionWithStateRepository extends JpaRepository<AuctionWithState, Long> {
    @Query(value = "select a.* from v_auction_with_state a where state_id = 1 order by start_date desc",nativeQuery = true)
    Optional<List<AuctionWithState>> getAuctionListDesc();

    List<AuctionWithState> findAllByEndDateIsAfter(Timestamp time);
    Page<AuctionWithState> findAllByOrderByIdDesc(Pageable pageable);
}
