package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Model.App_user;
import mg.cloud.enchere_back_end.Model.Bid_history;
import mg.cloud.enchere_back_end.Model.V_app_user;
import mg.cloud.enchere_back_end.Response.ErrorResponse;
import mg.cloud.enchere_back_end.Service.App_userService;
import mg.cloud.enchere_back_end.Service.AuctionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    private final AuctionService auctionService;
    private final App_userService app_userService;

    public AuctionController(AuctionService auctionService, App_userService app_userService) {
        this.auctionService = auctionService;
        this.app_userService = app_userService;
    }

    @PostMapping("/bid/{app_userid}&{bidid}&{amount}&{date}")
    public ResponseEntity<?> bid(@PathVariable("app_userid") Long app_userid,@PathVariable("bidid") Long bidid,@PathVariable("amount") float amount,@PathVariable("date") Timestamp date){
        auctionService.closedAuction();
        App_user user = app_userService.findById(app_userid);
        V_app_user v_app_user = auctionService.getV_app_user(user.getId());
        if(v_app_user==null){
            v_app_user = new V_app_user();
            v_app_user.setUser(user);
            v_app_user.setMoney_can_use(user.getAccount_balance());
        }

        Bid_history bidHistorySecondToLast = auctionService.getSecondToLastBid(bidid);
        App_user userSecondToLast = null;
        if(bidHistorySecondToLast!=null) {
            userSecondToLast = new App_user();
            userSecondToLast.setId(bidHistorySecondToLast.getAppUser().getId());
            userSecondToLast.setUsername(bidHistorySecondToLast.getAppUser().getUsername());
            userSecondToLast.setEmail(bidHistorySecondToLast.getAppUser().getEmail());
            userSecondToLast.setPassword(bidHistorySecondToLast.getAppUser().getPassword());
            userSecondToLast.setAccount_balance(bidHistorySecondToLast.getAppUser().getAccount_balance());
            userSecondToLast.setAccount_balance(userSecondToLast.getAccount_balance() + bidHistorySecondToLast.getAmount());
        }

        Bid_history bid_history = new Bid_history();
        bid_history.setAppUser(user);
        bid_history.setBidId(auctionService.findById(bidid));
        bid_history.setAmount(amount);
        bid_history.setDate(date);
        try{
            if(auctionService.verifyAuction(bid_history)) {
                if (auctionService.haveAmount(bid_history,v_app_user)) {
                    if(!auctionService.haveBid_step(bid_history)) {
                        if(auctionService.verifyAmountInBid_step(bid_history)){

                            app_userService.saveApp_user(user);
                            auctionService.saveBid_history(bid_history);
                        }
                    }else {

                            app_userService.saveApp_user(user);
                            auctionService.saveBid_history(bid_history);
                    }
                }
            }
        }catch (Exception e){
            ErrorResponse response = new ErrorResponse(404, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bid_history, HttpStatus.OK);
    }
}
