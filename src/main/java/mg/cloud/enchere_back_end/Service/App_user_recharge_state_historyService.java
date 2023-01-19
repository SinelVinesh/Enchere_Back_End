package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.App_user_recharge_request;
import mg.cloud.enchere_back_end.Model.App_user_recharge_state_history;
import mg.cloud.enchere_back_end.Repository.App_user_recharge_requestRepository;
import mg.cloud.enchere_back_end.Repository.App_user_recharge_state_historyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class App_user_recharge_state_historyService {
    @Autowired
    private App_user_recharge_state_historyRepository app_user_recharge_state_historyRepository;

    public App_user_recharge_state_history saveApp_user_recharge_state_history(App_user_recharge_state_history app_user_recharge_state_history){
        return app_user_recharge_state_historyRepository.save(app_user_recharge_state_history);
    }

    public App_user_recharge_state_history findById(Long id){
        return app_user_recharge_state_historyRepository.findById(id).get();
    }
    public List<App_user_recharge_state_history> getRechargeRequest(){
        return app_user_recharge_state_historyRepository.getRechargeRequest().orElse(null);
    }
}
