package mg.cloud.enchere_back_end.inputs;

import lombok.Getter;
import lombok.Setter;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;

@Getter
@Setter
public class AppUserInput {
    private String username;
    private String password;
    private String email;
    private LocalDate birthDate;

    public void setUsername(String username) throws InvalidValueException {
        if(username == null) {
            throw new InvalidValueException("Veuillez fournir un nom d'utilisateur");
        }
        // verifier que le nom d'utilisateur ne contient que des lettres, des chiffres, des tirets, des tirets du bas et des points
        if(!username.matches("^[a-zA-Z0-9._-]+$")) {
            throw new InvalidValueException("Le nom d'utilisateur ne doit contenir que des lettres, des chiffres, des tirets, des tirets du bas et des points");
        }
        if(username.length() < 4) {
            throw new InvalidValueException("Le nom d'utilisateur doit contenir au moins 4 caractères");
        }
        if(username.length() > 20) {
            throw new InvalidValueException("Le nom d'utilisateur doit contenir au plus 20 caractères");
        }
        this.username = username;
    }

    public void setPassword(String password) throws InvalidValueException {
        // verifier que le mot de passe contient au moins 8 caractères, au moins une lettre minuscule, au moins une lettre majuscule, au moins un chiffre et au moins un caractère spécial
        if(password != null && !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&/])[A-Za-z\\d@$!%*?&/]{8,}$")) {
            throw new InvalidValueException("Le mot de passe doit contenir au moins 8 caractères, au moins une lettre minuscule, au moins une lettre majuscule, au moins un chiffre et au moins un caractère spécial");
        }
        // crypter le mot de passe en utilisant SHA1

        this.password = DigestUtils.sha1Hex(password);;
    }

    public void setEmail(String email) throws InvalidValueException {
        // verifier que l'email est valide
        if(email != null && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidValueException("L'adresse email n'est pas valide");
        }
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) throws InvalidValueException {
        // verifier que l'utilisateur a plus de 18 ans
        if(birthDate != null && birthDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new InvalidValueException("Vous devez avoir plus de 18 ans pour vous inscrire");
        }
        this.birthDate = birthDate;
    }
}
