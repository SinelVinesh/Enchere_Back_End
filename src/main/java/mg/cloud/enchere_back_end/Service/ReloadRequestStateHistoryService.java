package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.ReloadRequestStateHistory;
import mg.cloud.enchere_back_end.Repository.ReloadRequestStateHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReloadRequestStateHistoryService {
    @Autowired
    private ReloadRequestStateHistoryRepository reloadRequestStateHistoryRepository;

    public ReloadRequestStateHistory saveReloadRequestHistory(ReloadRequestStateHistory reloadRequestStateHistory){
        return reloadRequestStateHistoryRepository.save(reloadRequestStateHistory);
    }

    public ReloadRequestStateHistory findById(Long id){
        return reloadRequestStateHistoryRepository.findById(id).get();
    }
    public List<ReloadRequestStateHistory> getRechargeRequest(){
        return reloadRequestStateHistoryRepository.getRechargeRequest().orElse(null);
    }

}
