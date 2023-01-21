package mg.cloud.enchere_back_end.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="v_auction_with_state")
@Getter
@Setter
public class AuctionWithState {
    @Id
    private Long id;

    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private App_user appUser;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "start_date",nullable = false)
    private Timestamp startDate;

    @Column(name = "end_date",nullable = false)
    private Timestamp endDate;

    @Column(name = "starting_price",nullable = false)
    private Float startingPrice;

    @Column(name = "bid_step")
    private Float bidStep;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private AuctionState auctionState;

    @Transient
    private Bid_history topBid;

    @Transient
    private List<Bid_history> history;
}
