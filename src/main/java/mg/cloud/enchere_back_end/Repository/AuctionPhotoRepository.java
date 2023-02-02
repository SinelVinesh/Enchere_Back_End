package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.AuctionPhoto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AuctionPhotoRepository extends MongoRepository<AuctionPhoto, String> {
    @Query("{auctionId: ?0}")
    List<AuctionPhoto> findByAuctionId(Long auctionId);
}
