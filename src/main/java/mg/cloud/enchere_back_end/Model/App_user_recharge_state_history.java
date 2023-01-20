package mg.cloud.enchere_back_end.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "app_user_recharge_state_history")
public class App_user_recharge_state_history {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date",nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "app_user_recharge_requestid")
    private App_user_recharge_request appUserRechargeRequest;

    @ManyToOne
    @JoinColumn(name = "recharge_stateid")
    private Recharge_state rechargeState;

}
