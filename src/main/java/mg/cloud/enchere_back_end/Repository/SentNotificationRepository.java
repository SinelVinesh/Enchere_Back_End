package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.SentNotification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SentNotificationRepository extends MongoRepository<SentNotification, String> {

}
