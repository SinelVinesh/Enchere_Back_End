package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.UserPhoto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserPhotoRepository extends MongoRepository<UserPhoto, String> {
    @Query("{userId: ?0}")
    Optional<UserPhoto> findByUserId(Long userId);
}
