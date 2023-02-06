package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.AuctionState;
import mg.cloud.enchere_back_end.Model.AuctionWithState;
import mg.cloud.enchere_back_end.Model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface AuctionStateRepository extends JpaRepository<AuctionState,Long> {
    Optional<AuctionState> findById(Long id);

    List<AuctionState> findAll();


}
