package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.BidHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Bid_historyRepository extends JpaRepository<BidHistory,Long> {
    @Query(value = "select b.id,b.amount,b.date,b.app_user_id,b.auction_id from bid_history b where b.id = (select max(id) from bid_history where auction_id = :bidid)",nativeQuery = true)
    Optional<BidHistory> getSecondToLastBid(Long bidid);

    @Query(value = "select b.* from auction a join bid_history b on b.auction_id = a.id where a.END_DATE <= NOW() and a.auction_stateid = 1 and b.id in (SELECT MAX(B.ID) FROM BID_HISTORY B GROUP BY B.auction_id)",nativeQuery = true)
    Optional<List<BidHistory>> getAuctionNotClosed();

    Optional<BidHistory> findFirstByAuctionIdOrderByDateDesc(Long auctionId);

    Optional<List<BidHistory>> findByAuctionIdOrderByDateDesc(Long auctionId);

    @Query(value = "select count(auction_id) from bid_history where auction_id = :auctionId",nativeQuery = true)
    Integer miseCount(Long auctionId);

    @Query(value = "select * from bid_history where auction_id = :auctionId and app_user_id = :id order by date desc LIMIT 1",nativeQuery = true)
    Optional<BidHistory> getLastMise(Long id,Long auctionId);
}
