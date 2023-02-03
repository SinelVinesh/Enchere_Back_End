package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.AuctionWithState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Timestamp;
import java.util.List;

public interface AuctionWithStateRepository extends JpaRepository<AuctionWithState, Long> {
    List<AuctionWithState> findAllByEndDateIsAfter(Timestamp time);
    Page<AuctionWithState> findAllByOrderByIdDesc(Pageable pageable);
}
