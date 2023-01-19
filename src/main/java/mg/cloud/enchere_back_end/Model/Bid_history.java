package mg.cloud.enchere_back_end.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "bid_history")
public class Bid_history {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount",nullable = false)
    private float amount;

    @Column(name = "date",nullable = false)
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "app_userid")
    @JsonBackReference
    private App_user appUser;

    @ManyToOne
    @JoinColumn(name = "bidid")
    @JsonBackReference
    private Auction bidId;

}
