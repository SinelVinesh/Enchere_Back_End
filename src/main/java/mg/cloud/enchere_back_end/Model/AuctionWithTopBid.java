package mg.cloud.enchere_back_end.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "v_auction_with_top_bid")
public class AuctionWithTopBid {
    @Id
    @Column(name = "auction_id")
    private Long id;
    private String description;
    @OneToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;
    @Column(name = "start_date", columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime startDate;
    @Column(name = "end_date", columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime endDate;
    @Column(name = "starting_price")
    private Float startingPrice;
    private String title;
    @OneToOne
    @JoinColumn(name = "top_bid_id")
    private BidHistory topBid;
    @OneToOne
    @JoinColumn(name = "state_id")
    private AuctionState state;
    @OneToOne
    @JoinColumn(name = "commission_rate_id")
    private SettingsValueHistory commissionRate;
    @Column(name = "commission_amount")
    private Float commissionAmount;
}
