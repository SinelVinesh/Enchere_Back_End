package mg.cloud.enchere_back_end.Controller;

import jakarta.servlet.http.HttpServletRequest;
import mg.cloud.enchere_back_end.Model.Settings;
import mg.cloud.enchere_back_end.Model.SettingsValueHistory;
import mg.cloud.enchere_back_end.Repository.SettingValueHistoryRepository;
import mg.cloud.enchere_back_end.Repository.SettingsRepository;
import mg.cloud.enchere_back_end.Service.CrudService;
import mg.cloud.enchere_back_end.Service.SettingsService;
import mg.cloud.enchere_back_end.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class SettingsController {
    private final SettingsRepository settingsRepository;
    private final CrudService<Settings, Long> crudServiceSettings;
    private final SettingValueHistoryRepository settingValueHistoryRepository;
    private final SettingsService settingsService;

    public SettingsController(
            SettingsRepository settingsRepository,
            CrudService<Settings, Long> crudServiceSettings,
            SettingValueHistoryRepository settingValueHistoryRepository,
            SettingsService settingsService
    ) {
        this.settingsRepository = settingsRepository;
        this.crudServiceSettings = crudServiceSettings;
        this.settingValueHistoryRepository = settingValueHistoryRepository;
        this.settingsService = settingsService;
    }

    @RequestMapping(value = {"/settings", "/settings/{id}"})
    public ResponseEntity<Response> crudCategories (
            @PathVariable(value = "id") Optional<Long> id,
            @RequestBody Optional<Settings> settings,
            HttpServletRequest request
            ) {
        Settings data = settings.orElse(null);
        ResponseEntity<Response> response = crudServiceSettings.handle(request.getMethod(), settingsRepository, id, data);
        if(request.getMethod().equals("PUT") && id.isPresent() && data != null) {
            Settings currentSettings = settingsRepository.findById(id.get()).orElse(null);
            settingsService.fillSettings(currentSettings);
            if(currentSettings != null && currentSettings.getCurrentValue().getValue().equals(data.getCurrentValue().getValue())) {
                return response;
            }
            Settings newSettings = (Settings)response.getBody().getData();
            SettingsValueHistory settingsValueHistory = new SettingsValueHistory();
            settingsValueHistory.setSetting(newSettings);
            settingsValueHistory.setValue(data.getCurrentValue().getValue());
            settingsValueHistory.setDate(LocalDateTime.now());
            settingValueHistoryRepository.save(settingsValueHistory);
        }
        if(request.getMethod().equals("POST")) {
            Settings newSettings = (Settings)response.getBody().getData();
            SettingsValueHistory settingsValueHistory = new SettingsValueHistory();
            settingsValueHistory.setSetting(newSettings);
            settingsValueHistory.setValue(data.getCurrentValue().getValue());
            settingsValueHistory.setDate(LocalDateTime.now());
            settingValueHistoryRepository.save(settingsValueHistory);
        }
        if(request.getMethod().equals("GET") && response.getBody() != null) {
            settingsService.fillSettings(response.getBody().getData());
            if (id.isPresent()) {
                settingsService.fillHistory(response.getBody().getData());
            }
        }
        return response;
    }
}
