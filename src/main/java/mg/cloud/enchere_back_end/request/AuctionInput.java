package mg.cloud.enchere_back_end.request;

import lombok.Getter;
import lombok.Setter;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Getter
@Setter
public class AuctionInput {
    private String title;
    private String description;
    private Long categoryId;
    private Float startingPrice;
    private LocalDateTime startDate;
    private Float bidStep;
    private List<Photo> images;

    public void setTitle(String title) throws InvalidValueException {
        // verifier que le titre n'est pas vide
        if (title == null || title.isEmpty()) {
            throw new InvalidValueException("Le titre ne peut pas être vide");
        }
        // verifier que le titre est entre 5 et 50 caractères
        if (title.length() < 5 || title.length() > 50) {
            throw new InvalidValueException("Le titre doit être entre 5 et 50 caractères");
        }
        this.title = title;
    }

    public void setDescription(String description) throws InvalidValueException {
        // verifier que la description n'est pas vide
        if (description == null || description.isEmpty()) {
            throw new InvalidValueException("La description ne peut pas être vide");
        }
        // verifier que la description est entre 5 et 500 caractères
        if (description.length() > 500) {
            throw new InvalidValueException("La description doit contenir au plus 500 caractères");
        }
        this.description = description;
    }

    public void setStartingPrice(Float startingPrice) throws InvalidValueException {
        // verifier que le prix de depart n'est pas vide
        if (startingPrice == null) {
            throw new InvalidValueException("Le prix de départ ne peut pas être vide");
        }
        // verifier que le prix de départ est positif
        if (startingPrice < 100) {
            throw new InvalidValueException("Le prix de départ doit être supérieur à 100 Ar");
        }
        this.startingPrice = startingPrice;
    }

    public void setStartDate(LocalDateTime startDate) throws InvalidValueException {
        startDate = startDate.plus(3,ChronoUnit.HOURS);
        // verifier que la date de début n'est pas vide
        if (startDate == null) {
            throw new InvalidValueException("La date de début ne peut pas être vide");
        }
        // verifier que la date de début est dans le futur
        if (startDate.isBefore(LocalDateTime.now().minus(20, ChronoUnit.MINUTES))) {
            throw new InvalidValueException("La date de début doit être dans le futur");
        }
        this.startDate = startDate;
    }

    public void setBidStep(Float bidStep) throws InvalidValueException {
        //verifier que le pas de l'enchère n'est pas vide
        if (bidStep == null) {
            throw new InvalidValueException("Le pas de l'enchère ne peut pas être vide");
        }
        // verifier que le pas de l'enchère est positif
        if (bidStep < 1) {
            throw new InvalidValueException("Le pas de l'enchère doit être positif");
        }
        this.bidStep = bidStep;
    }

    public void setPhotos(List<Photo> photos) throws InvalidValueException {
        // verifier que la liste de photos n'est pas vide
        if (photos == null || photos.isEmpty()) {
            throw new InvalidValueException("La liste de photos ne peut pas être vide");
        }
    }
}
