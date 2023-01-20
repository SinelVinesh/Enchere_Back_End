package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.Bid_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Bid_historyRepository extends JpaRepository<Bid_history,Long> {
    @Query(value = "select b.id,b.amount,b.date,b.app_userid,b.bidid from bid_history b where b.id = (select max(id) from bid_history where bidid = :bidid)",nativeQuery = true)
    Optional<Bid_history> getSecondToLastBid(Long bidid);

    @Query(value = "select b.* from auction a join bid_history b on b.bidid = a.id where a.END_DATE <= NOW() and a.auction_stateid = 1 and b.id in (SELECT MAX(B.ID) FROM BID_HISTORY B GROUP BY B.BIDID)",nativeQuery = true)
    Optional<List<Bid_history>> getAuctionNotClosed();
}