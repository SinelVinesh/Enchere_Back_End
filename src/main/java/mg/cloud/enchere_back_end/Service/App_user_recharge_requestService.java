package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.App_user_recharge_request;
import mg.cloud.enchere_back_end.Model.App_user_recharge_state_history;
import mg.cloud.enchere_back_end.Repository.App_user_recharge_requestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class App_user_recharge_requestService {
    @Autowired
    private App_user_recharge_requestRepository app_user_recharge_requestRepository;

    public App_user_recharge_request saveApp_user_recharge_request(App_user_recharge_request appUserRechargeRequest){
        return app_user_recharge_requestRepository.save(appUserRechargeRequest);
    }
    public App_user_recharge_request findById(Long id){
        return app_user_recharge_requestRepository.findById(id).get();
    }
}
