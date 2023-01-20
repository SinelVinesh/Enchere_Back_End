package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Model.App_user_recharge_request;
import mg.cloud.enchere_back_end.Model.App_user_recharge_state_history;
import mg.cloud.enchere_back_end.Model.Recharge_state;
import mg.cloud.enchere_back_end.response.Response;
import mg.cloud.enchere_back_end.Service.App_user_recharge_requestService;
import mg.cloud.enchere_back_end.Service.App_user_recharge_state_historyService;
import mg.cloud.enchere_back_end.Service.Recharge_StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/app_user_recharge_request")
public class App_user_recharge_requestController {
    private final App_user_recharge_requestService app_user_recharge_requestService;
    private final App_user_recharge_state_historyService app_user_recharge_state_historyService;
    private final Recharge_StateService recharge_stateService;

    public App_user_recharge_requestController(App_user_recharge_requestService app_user_recharge_requestService, App_user_recharge_state_historyService app_user_recharge_state_historyService, Recharge_StateService recharge_stateService) {
        this.app_user_recharge_requestService = app_user_recharge_requestService;
        this.app_user_recharge_state_historyService = app_user_recharge_state_historyService;
        this.recharge_stateService = recharge_stateService;
    }
    @PostMapping(
            value="/save",
            consumes= {MediaType.APPLICATION_JSON_VALUE},
            produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> saveRecharge_request( @RequestBody App_user_recharge_request app_user_recharge_request) {
        try {
                App_user_recharge_request appUserRechargeRequest = app_user_recharge_requestService.saveApp_user_recharge_request(app_user_recharge_request);
                App_user_recharge_state_history app_user_recharge_state_history = new App_user_recharge_state_history();
                app_user_recharge_state_history.setDate(appUserRechargeRequest.getDate());
                app_user_recharge_state_history.setAppUserRechargeRequest(appUserRechargeRequest);
                Recharge_state rechargeState = recharge_stateService.findById(Long.valueOf(1));
                app_user_recharge_state_history.setRechargeState(rechargeState);
                app_user_recharge_state_historyService.saveApp_user_recharge_state_history(app_user_recharge_state_history);

                HashMap<String,Object> responseData = new HashMap<>();
                responseData.put("app_user_recharge_request",appUserRechargeRequest);
                responseData.put("app_user_recharge_state_history",app_user_recharge_state_history);
                responseData.put("recharge_state",rechargeState);

                return new ResponseEntity<>(appUserRechargeRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            Response response = new Response("failed to save the recharge request");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
