package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Model.*;
import mg.cloud.enchere_back_end.response.Response;
import mg.cloud.enchere_back_end.Service.App_userService;
import mg.cloud.enchere_back_end.Service.App_user_recharge_requestService;
import mg.cloud.enchere_back_end.Service.App_user_recharge_state_historyService;
import mg.cloud.enchere_back_end.Service.Recharge_StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/app_user_state_history")
public class App_user_recharge_state_historyController{
    private final App_user_recharge_state_historyService app_user_recharge_state_historyService;
    private final Recharge_StateService recharge_stateService;
    private final App_user_recharge_requestService app_user_recharge_requestService;
    private final App_userService app_userService;
    public App_user_recharge_state_historyController(App_user_recharge_state_historyService app_user_recharge_state_historyService, Recharge_StateService recharge_stateService, App_user_recharge_requestService app_user_recharge_requestService, App_userService app_userService) {
        this.app_user_recharge_state_historyService = app_user_recharge_state_historyService;
        this.recharge_stateService = recharge_stateService;
        this.app_user_recharge_requestService = app_user_recharge_requestService;
        this.app_userService = app_userService;
    }

    @PostMapping("/cancel/{app_user_recharge_requestid}")
    public ResponseEntity<?> cancelRecharge_request(@PathVariable("app_user_recharge_requestid") Long id) {
        try{
            App_user_recharge_state_history app_user_recharge_state_history = new App_user_recharge_state_history();
            Recharge_state rechargeState = recharge_stateService.findById(Long.valueOf(3));
            app_user_recharge_state_history.setRechargeState(rechargeState);
            app_user_recharge_state_history.setDate(Date.valueOf(LocalDate.now()));
            App_user_recharge_request appUserRechargeRequest = app_user_recharge_requestService.findById(id);
            app_user_recharge_state_history.setAppUserRechargeRequest(appUserRechargeRequest);
            app_user_recharge_state_historyService.saveApp_user_recharge_state_history(app_user_recharge_state_history);
            return new ResponseEntity<>(app_user_recharge_state_history, HttpStatus.CREATED);
        }catch(Exception e){
            Response response = new Response("failed to cancel the recharge request");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/confirm/{app_user_recharge_requestid}")
    public ResponseEntity<?> confirmRecharge_request(@PathVariable("app_user_recharge_requestid") Long id) {
        try{
            App_user_recharge_state_history app_user_recharge_state_history = new App_user_recharge_state_history();
            Recharge_state rechargeState = recharge_stateService.findById(Long.valueOf(2));
            app_user_recharge_state_history.setRechargeState(rechargeState);
            app_user_recharge_state_history.setDate(Date.valueOf(LocalDate.now()));
            App_user_recharge_request appUserRechargeRequest = app_user_recharge_requestService.findById(id);
            app_user_recharge_state_history.setAppUserRechargeRequest(appUserRechargeRequest);
            app_user_recharge_state_historyService.saveApp_user_recharge_state_history(app_user_recharge_state_history);

            App_user user = app_user_recharge_state_history.getAppUserRechargeRequest().getUser();
            Float account_blance = user.getAccount_balance() + app_user_recharge_state_history.getAppUserRechargeRequest().getAmount();

            user.setAccount_balance(account_blance);
            app_userService.saveApp_user(user);
            return new ResponseEntity<>(app_user_recharge_state_history, HttpStatus.CREATED);
        }catch(Exception e){
            Response response = new Response("failed to cancel the recharge request");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/rechargeRequests")
    public ResponseEntity<?> getRechargeRequest() {
        List<App_user_recharge_state_history> app_user_recharge_state_histories = app_user_recharge_state_historyService.getRechargeRequest();
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
