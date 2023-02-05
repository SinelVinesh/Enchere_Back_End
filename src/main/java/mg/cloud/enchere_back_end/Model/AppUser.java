package mg.cloud.enchere_back_end.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

}

