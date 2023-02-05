package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.SettingsValueHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SettingValueHistoryRepository extends JpaRepository<SettingsValueHistory, Long> {
    Optional<SettingsValueHistory> findFirstBySettingIdOrderByDateDesc(Long id);

    Optional<List<SettingsValueHistory>> findBySettingIdOrderByDateDesc(Long id);
}
