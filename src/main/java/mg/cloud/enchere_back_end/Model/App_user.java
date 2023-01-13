package mg.cloud.enchere_back_end.Model;

import jakarta.persistence.*;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "app_user")
public class App_user {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Size(min=3)
    @Column(name = "username",nullable = false)
    private String username;

    @Size(min=8)
    @Pattern(regexp = "^(?=.*[A-Z]).+$", message = "Le mot de passe doit contenir au moins une majuscule")
    @Column(name = "password",nullable = false)
    private String password;

    @Email
    @Column(name = "email",nullable = false)
    private String email;

    @Min(0)
    @Column(name = "account_balance",nullable = false)
    private Float account_balance;

}

