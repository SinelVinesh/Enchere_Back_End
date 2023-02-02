package mg.cloud.enchere_back_end.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "app_user_token")
public class AppUserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "value",nullable = false)
    private String value;

    @JsonIgnore
    @Column(name = "expiration_date",nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime expirationDate;

    @JsonIgnore
    @Column(name = "creation_date",nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name="app_userid")
    private AppUser user;

}
