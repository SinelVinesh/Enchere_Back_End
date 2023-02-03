package mg.cloud.enchere_back_end.request;

import lombok.Getter;
import lombok.Setter;
import mg.cloud.enchere_back_end.Model.AppUser;
import mg.cloud.enchere_back_end.Model.AuctionWithState;

@Getter
@Setter
public class Notification {
    private String message;
    private Long auctionId;
    private Long userId;
    private String token;
}
