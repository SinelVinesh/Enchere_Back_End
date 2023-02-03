package mg.cloud.enchere_back_end.inputs;

import lombok.Getter;
import lombok.Setter;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tika.Tika;

import java.util.Base64;

@Getter
@Setter
public class AppUserUpdateInput {
    private String username;
    private String email;
    private String password;
    private Photo photo;

    public void setUsername(String username) throws InvalidValueException {
        if(username == null || username.isEmpty()) {
            username = null;
            return;
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
        if(password == null || password.isEmpty()) {
            password = null;
            return;
        }
        // verifier que le mot de passe contient au moins 8 caractères, au moins une lettre minuscule, au moins une lettre majuscule, au moins un chiffre et au moins un caractère spécial
        if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&/])[A-Za-z\\d@$!%*?&/]{8,}$")) {
            throw new InvalidValueException("Le mot de passe doit contenir au moins 8 caractères, au moins une lettre minuscule, au moins une lettre majuscule, au moins un chiffre et au moins un caractère spécial");
        }
        // crypter le mot de passe en utilisant SHA1

        this.password = DigestUtils.sha1Hex(password);;
    }

    public void setEmail(String email) throws InvalidValueException {
        if(email == null || email.isEmpty()) {
            email = null;
            return;
        }
        // verifier que l'email est valide
        if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidValueException("L'adresse email n'est pas valide");
        }
        this.email = email;
    }

    public void setPhoto(Photo photo) throws InvalidValueException {
        if(photo == null){
            this.photo = null;
            return;
        }
        byte[] decodedBytes = Base64.getDecoder().decode(photo.getBase64String());
        Tika tika = new Tika();
        String mimeType = tika.detect(decodedBytes).split("/")[0];
        if(!mimeType.equals("image")){
            throw new InvalidValueException("Le fichier envoyé n'est pas une image");
        }
        this.photo = photo;
    }
}
