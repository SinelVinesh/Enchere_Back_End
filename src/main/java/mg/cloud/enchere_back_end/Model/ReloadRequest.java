package mg.cloud.enchere_back_end.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "reload_request")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ReloadRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "amount",nullable = false)
    private Float amount;

    @ManyToOne
    @JoinColumn(name= "app_user_id")
    private AppUser user;

    @Transient
    private ReloadRequestStateHistory currentState;
}
