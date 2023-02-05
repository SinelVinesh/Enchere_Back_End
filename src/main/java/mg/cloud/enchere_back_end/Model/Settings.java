package mg.cloud.enchere_back_end.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String key;
    @Column(name = "creation_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Transient
    private SettingsValueHistory currentValue;

    @Transient
    private List<SettingsValueHistory> history;

    public static class AuctionWithMise {
    }
}
