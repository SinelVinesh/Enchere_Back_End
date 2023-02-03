package mg.cloud.enchere_back_end.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AppUserWithPhoto {
    private Long id;

    private String username;

    private String email;

    @JsonBackReference
    private String password;

    private LocalDate birthDate;

    private LocalDate registrationDate;

    private UserPhoto photo;
    private float balance;
    private float usableBalance;

}
