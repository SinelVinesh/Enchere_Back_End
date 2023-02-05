package mg.cloud.enchere_back_end.Model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("auctionphoto")
public class AuctionPhoto {
    @Id
    private String id;

    private Long auctionId;
    private String photoPath;

}
