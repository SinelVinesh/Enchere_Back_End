package mg.cloud.enchere_back_end.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "v_daily_turnover")
public class DailyTurnover {
    @Id
    private Long id;
    private Float turnover;
    @Column(columnDefinition = "DATE")
    private LocalDate date;
}
