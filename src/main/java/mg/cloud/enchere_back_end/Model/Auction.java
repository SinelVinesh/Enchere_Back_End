package mg.cloud.enchere_back_end.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "auction")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "start_date",nullable = false, columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime startDate;

    @Column(name = "end_date",nullable = false, columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime endDate;

    @Column(name = "starting_price",nullable = false)
    private Float startingPrice;

    @Column(name = "bid_step")
    private Float bidStep;

    @ManyToOne
    @JoinColumn(name = "commission_rate_id")
    private SettingsValueHistory commissionRate;
}
