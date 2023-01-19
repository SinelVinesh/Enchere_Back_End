package mg.cloud.enchere_back_end.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.w3c.dom.views.AbstractView;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "v_app_user")
public class V_app_user {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "app_userid")
    @JsonBackReference
    private App_user user;

    @Column(name = "money_can_use")
    private Float money_can_use;
}
