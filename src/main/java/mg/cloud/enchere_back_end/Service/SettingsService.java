package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.Settings;
import mg.cloud.enchere_back_end.Model.SettingsValueHistory;
import mg.cloud.enchere_back_end.Repository.SettingValueHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingsService {
    private SettingValueHistoryRepository settingValueHistoryRepository;

    public SettingsService(
            SettingValueHistoryRepository settingValueHistoryRepository
    ) {
        this.settingValueHistoryRepository = settingValueHistoryRepository;
    }

    public SettingsValueHistory getCurrentValue(Long id) {
        return settingValueHistoryRepository.findFirstBySettingIdOrderByDateDesc(id).orElse(null);
    }

    private List<SettingsValueHistory> getHistory(Long id) {
        return settingValueHistoryRepository.findBySettingIdOrderByDateDesc(id).orElse(null);
    }

    public void fillSettings(Object settings) {
        if(settings instanceof List) {
            List<Settings> settingsList = (List<Settings>) settings;
            for (Settings setting : settingsList) {
                setting.setCurrentValue(this.getCurrentValue(setting.getId()));
            }
        } else {
            Settings setting = (Settings) settings;
            setting.setCurrentValue(this.getCurrentValue(setting.getId()));
        }
    }

    public void fillHistory(Object settings) {
        if(settings instanceof List) {
            List<Settings> settingsList = (List<Settings>) settings;
            for (Settings setting : settingsList) {
                setting.setHistory(this.getHistory(setting.getId()));
            }
        } else {
            Settings setting = (Settings) settings;
            setting.setHistory(this.getHistory(setting.getId()));
        }
    }
}
