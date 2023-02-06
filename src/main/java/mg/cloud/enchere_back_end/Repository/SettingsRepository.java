package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
    Settings findFirstById(Long id);
}
