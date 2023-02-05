package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Model.*;
import mg.cloud.enchere_back_end.response.Response;
import mg.cloud.enchere_back_end.Service.AppUserService;
import mg.cloud.enchere_back_end.Service.ReloadRequestService;
import mg.cloud.enchere_back_end.Service.ReloadRequestStateHistoryService;
import mg.cloud.enchere_back_end.Service.Recharge_StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/app_user_state_history")
public class ReloadRequestStateHistoryController {
    private final ReloadRequestStateHistoryService reloadRequestStateHistoryService;
    private final Recharge_StateService recharge_stateService;
    private final ReloadRequestService reloadRequestService;
    private final AppUserService app_userService;
    public ReloadRequestStateHistoryController(ReloadRequestStateHistoryService reloadRequestStateHistoryService, Recharge_StateService recharge_stateService, ReloadRequestService reloadRequestService, AppUserService app_userService) {
        this.reloadRequestStateHistoryService = reloadRequestStateHistoryService;
        this.recharge_stateService = recharge_stateService;
        this.reloadRequestService = reloadRequestService;
        this.app_userService = app_userService;
    }


    @PostMapping("/cancel/{app_user_recharge_requestid}")
    public ResponseEntity<?> cancelRecharge_request(@PathVariable("app_user_recharge_requestid") Long id) {
        try{
            ReloadRequestStateHistory reloadRequestStateHistory = new ReloadRequestStateHistory();
            ReloadState rechargeState = recharge_stateService.findById(Long.valueOf(3));
            reloadRequestStateHistory.setReloadState(rechargeState);
            reloadRequestStateHistory.setDate(LocalDateTime.now());
            ReloadRequest appUserRechargeRequest = reloadRequestService.findById(id);
            reloadRequestStateHistory.setReloadRequest(appUserRechargeRequest);
            reloadRequestStateHistoryService.saveReloadRequestHistory(reloadRequestStateHistory);
            return new ResponseEntity<>(reloadRequestStateHistory, HttpStatus.CREATED);
        }catch(Exception e){
            Response response = new Response("failed to cancel the recharge request");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/confirm/{app_user_recharge_requestid}")
    public ResponseEntity<?> confirmRecharge_request(@PathVariable("app_user_recharge_requestid") Long id) {
        try{
            ReloadRequestStateHistory reloadRequestStateHistory = new ReloadRequestStateHistory();
            ReloadState rechargeState = recharge_stateService.findById(Long.valueOf(2));
            reloadRequestStateHistory.setReloadState(rechargeState);
            reloadRequestStateHistory.setDate(LocalDateTime.now());
            ReloadRequest appUserRechargeRequest = reloadRequestService.findById(id);
            reloadRequestStateHistory.setReloadRequest(appUserRechargeRequest);
            reloadRequestStateHistoryService.saveReloadRequestHistory(reloadRequestStateHistory);
            return new ResponseEntity<>(reloadRequestStateHistory, HttpStatus.CREATED);
        }catch(Exception e){
            Response response = new Response("failed to cancel the recharge request");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/rechargeRequests")
    public ResponseEntity<?> getRechargeRequest() {
        List<ReloadRequestStateHistory> app_user_recharge_state_histories = reloadRequestStateHistoryService.getRechargeRequest();
        if(app_user_recharge_state_histories!=null){
            HashMap<String, Object> responseData = new HashMap<>();
            responseData.put("reloads",app_user_recharge_state_histories);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            Response response = new Response("no recharge request");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
