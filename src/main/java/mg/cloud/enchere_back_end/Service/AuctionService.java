package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.*;
import mg.cloud.enchere_back_end.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {
    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private Bid_historyRepository bid_historyRepository;

    @Autowired
    private V_app_userRepository v_app_userRepository;
    @Autowired
    private AuctionStateRepository auctionStateRepository;
    @Autowired
    private App_userRepository app_userRepository;
    public Auction saveAuction(Auction auction){
        return auctionRepository.save(auction);
    }

    public Bid_history saveBid_history(Bid_history bid_history){
        return bid_historyRepository.save(bid_history);
    }
    public boolean haveAmount(Bid_history bid_history,V_app_user v_app_user) throws Exception {
        if((v_app_user.getMoney_can_use() < bid_history.getAmount()) || (bid_history.getAmount() < bid_history.getBidId().getStarting_price()) ) throw new Exception("your account balance is not valid");
       return true;
    }

    public boolean haveBid_step(Bid_history bid_history) {
        return ObjectUtils.isEmpty(bid_history.getBidId().getBid_step());

    }

    public boolean verifyAmountInBid_step(Bid_history bid_history) throws Exception {
        if(bid_history.getAmount() % bid_history.getBidId().getBid_step()!=0) throw new Exception("Your amount does not follow the bid-step");
        return true;
    }

    public boolean verifyAuction(Bid_history bid_history) throws Exception {
        if(bid_history.getBidId().getEnd_date().compareTo(bid_history.getDate()) < 0) throw new Exception("The Auction is no longer available");
        return true;
    }


    public Auction findById(Long id){
        return auctionRepository.findById(id).get();
    }

    public Bid_history getSecondToLastBid(Long id){
        if(bid_historyRepository.getSecondToLastBid(id).isPresent())return bid_historyRepository.getSecondToLastBid(id).get();
        return null;
    }

    public V_app_user getV_app_user(Long id){
       Optional<V_app_user> vap = v_app_userRepository.getV_app_user(id);
        return vap.orElse(null);
    }

    public List<Bid_history> getAuctionNotClosed(){
        return bid_historyRepository.getAuctionNotClosed().orElse(null);
    }

    public void closedAuction(){
        List<Bid_history> auction = this.getAuctionNotClosed();
        if(auction!=null){
            for (Bid_history bid_history : auction) {
                Auction au = bid_history.getBidId();
                au.setAuctionState(auctionStateRepository.findById(2L).get());
                App_user appUser = bid_history.getAppUser();
                V_app_user vAppUser = v_app_userRepository.getV_app_user(appUser.getId()).get();
                appUser.setAccount_balance(vAppUser.getMoney_can_use());
                app_userRepository.save(appUser);
                auctionRepository.save(au);
            }
        }
    }
}
