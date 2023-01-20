package mg.cloud.enchere_back_end.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "auction")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "app_userid")
    private App_user appUser;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "start_date",nullable = false)
    private Timestamp start_date;

    @Column(name = "end_date",nullable = false)
    private Timestamp end_date;

    @Column(name = "starting_price",nullable = false)
    private Float starting_price;

    @Column(name = "bid_step")
    private Float bid_step;

    @ManyToOne
    @JoinColumn(name = "auction_stateid")
    private AuctionState auctionState;
}
