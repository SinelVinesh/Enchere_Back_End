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
@Table(name = "app_user_token")
public class App_user_token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "value",nullable = false)
    private String value;

    @Column(name = "expiration_date",nullable = false)
    private Timestamp expiration_date;

    @Column(name = "creation_date",nullable = false)
    private Timestamp creation_date;

    @ManyToOne
    @JoinColumn(name="app_userid")
    @JsonBackReference
    private App_user user;
}
