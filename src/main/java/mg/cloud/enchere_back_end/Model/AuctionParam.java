package mg.cloud.enchere_back_end.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AuctionParam {
    private String description;
    private List<Category> category;
    private List<AppUser> username;
    private List<AuctionState> auctionState;
    private List<Float> prix;
    private Date startDate1;
    private Date startDate2;
    private Date endDate1;
    private Date endDate2;
}
