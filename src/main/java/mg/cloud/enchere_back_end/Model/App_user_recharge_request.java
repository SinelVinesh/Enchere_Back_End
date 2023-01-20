package mg.cloud.enchere_back_end.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "app_user_recharge_request")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class App_user_recharge_request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date",nullable = false)
    private Date date;

    @Column(name = "amount",nullable = false)
    private Float amount;

    @ManyToOne
    @JoinColumn(name="app_userid")
    private App_user user;
}
