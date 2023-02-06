package mg.cloud.enchere_back_end.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "settings_value_history")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class SettingsValueHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name= "settings_id")
    @JsonBackReference
    private Settings setting;
}
