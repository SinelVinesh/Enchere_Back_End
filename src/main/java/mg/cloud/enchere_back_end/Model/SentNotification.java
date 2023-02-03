package mg.cloud.enchere_back_end.Model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("sentauctionnotification")
public class SentNotification {
    @Id
    private String id;
    private Long auctionid;
    private Long appUserId;
    private Long auctionStateId;
}
