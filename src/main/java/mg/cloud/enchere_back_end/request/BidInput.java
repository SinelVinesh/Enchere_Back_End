package mg.cloud.enchere_back_end.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BidInput {
    @JsonProperty("app_userid")
    private Long appUserId;
    private Long bidId;
    private float amount;
}
