package mg.cloud.enchere_back_end.Repository;


import mg.cloud.enchere_back_end.Model.App_user;
import mg.cloud.enchere_back_end.Model.Auction;
import mg.cloud.enchere_back_end.Model.V_app_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuctionRepository extends JpaRepository<Auction,Long> {
    @Override
    Optional<Auction> findById(Long id);


}
