package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.ReloadRequest;
import mg.cloud.enchere_back_end.Model.ReloadRequestStateHistory;
import mg.cloud.enchere_back_end.Repository.ReloadRequestRepository;
import mg.cloud.enchere_back_end.Repository.ReloadRequestStateHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReloadRequestService {
    @Autowired
    private ReloadRequestRepository reloadRequestRepository;
    @Autowired
    private ReloadRequestStateHistoryRepository reloadRequestStateHistoryRepository;

    public ReloadRequest saveApp_user_recharge_request(ReloadRequest appUserRechargeRequest){
        return reloadRequestRepository.save(appUserRechargeRequest);
    }
    public ReloadRequest findById(Long id){
        return reloadRequestRepository.findById(id).get();
    }

    private ReloadRequestStateHistory getCurrenState(Long id){
        return reloadRequestStateHistoryRepository.findFirstByReloadRequestIdOrderByDateDesc(id).orElse(null);
    }
    public void fillReloadRequest(Object requests) {
        if(requests instanceof List) {
            List<ReloadRequest> reloadRequests = (List<ReloadRequest>) requests;
            for (ReloadRequest reloadRequest : reloadRequests) {
                reloadRequest.setCurrentState(this.getCurrenState(reloadRequest.getId()));
            }
        } else {
            ReloadRequest reloadRequest = (ReloadRequest) requests;
            reloadRequest.setCurrentState(this.getCurrenState(reloadRequest.getId()));
        }
    }
}
