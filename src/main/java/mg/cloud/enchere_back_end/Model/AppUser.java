package mg.cloud.enchere_back_end.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import javax.validation.constraints.*;

import lombok.*;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    @JsonBackReference
    private String password;

    @Email
    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "birth_date",nullable = false, columnDefinition = "DATE")
    private LocalDate birthDate;

    @Column(name = "registration_date",nullable = false, columnDefinition = "DATE")
    private LocalDate registrationDate;

    @Column(name = "account_balance",nullable = false)
    private float accountBalance;
}

