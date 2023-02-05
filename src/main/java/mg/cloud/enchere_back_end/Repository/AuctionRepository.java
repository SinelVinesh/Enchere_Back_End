package mg.cloud.enchere_back_end.Repository;


import mg.cloud.enchere_back_end.Model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuctionRepository extends JpaRepository<Auction,Long> {
    @Override
    Optional<Auction> findById(Long id);

    @Query(value = "select a.* from auction a join bid_history b on b.bidid = a.id where  A.END_DATE <= NOW() and b.id in (SELECT MAX(B.ID) FROM BID_HISTORY B GROUP BY B.BIDID)order by b.amount asc limit 1",nativeQuery = true)
    Optional<Auction> getLeastValuableAuction();

    @Query(value = "select a.* from auction a join bid_history b on b.bidid = a.id where b.id in (SELECT MAX(B.ID) FROM BID_HISTORY B GROUP BY B.BIDID)order by b.amount desc limit 1",nativeQuery = true)
    Optional<Auction> getMostValuableAuction();

    @Query(value = "select a.* from auction where app_user_id = :userid and id = :auctionid",nativeQuery = true)
    Optional<Auction> isUserAuction(Long userid,Long auctionid);

}
