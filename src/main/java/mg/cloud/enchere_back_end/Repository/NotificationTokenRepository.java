package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.NotificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NotificationTokenRepository extends MongoRepository<NotificationToken, String> {
    @Query("{userId:  ?0}")
    List<NotificationToken> findByUserId(Long userId);
}
