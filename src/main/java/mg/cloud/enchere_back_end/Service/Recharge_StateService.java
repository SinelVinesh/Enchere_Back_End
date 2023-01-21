package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.ReloadState;
import mg.cloud.enchere_back_end.Repository.Recharge_StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Recharge_StateService {
    @Autowired
    private Recharge_StateRepository recharge_stateRepository;

    public ReloadState findById(Long id){
        Optional<ReloadState> rechargeState = recharge_stateRepository.findById(id);
        if(rechargeState.isPresent()){
            return rechargeState.get();
        }
        return null;
    }
}
