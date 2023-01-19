package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.Recharge_state;
import mg.cloud.enchere_back_end.Repository.Recharge_StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Recharge_StateService {
    @Autowired
    private Recharge_StateRepository recharge_stateRepository;

    public Recharge_state findById(Long id){
        Optional<Recharge_state> rechargeState = recharge_stateRepository.findById(id);
        if(rechargeState.isPresent()){
            return rechargeState.get();
        }
        return null;
    }
}
