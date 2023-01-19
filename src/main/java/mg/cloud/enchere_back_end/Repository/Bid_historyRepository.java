package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.Bid_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface Bid_historyRepository extends JpaRepository<Bid_history,Long> {
    @Query(value = "select b.id,b.amount,b.date,b.app_userid,b.bidid from bid_history b where b.id = (select max(id) from bid_history where bidid = :bidid)",nativeQuery = true)
    Optional<Bid_history> getSecondToLastBid(Long bidid);
}
