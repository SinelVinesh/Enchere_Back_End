package mg.cloud.enchere_back_end.Service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import mg.cloud.enchere_back_end.Model.*;
import mg.cloud.enchere_back_end.Repository.*;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import mg.cloud.enchere_back_end.request.AuctionInput;
import mg.cloud.enchere_back_end.request.Photo;
import mg.cloud.enchere_back_end.response.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuctionService {
    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private Bid_historyRepository bid_historyRepository;

    @Autowired
    private AppUserFullBalanceRepository appUserFullBalanceRepository;
    @Autowired
    private AuctionStateRepository auctionStateRepository;
    @Autowired
    private AppUserRepository app_userRepository;
    @Autowired
    private AuctionWithStateRepository auctionWithStateRepository;
    @Autowired
    private AppUserTokenRepository appUserTokenRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SettingsRepository settingsRepository;
    @Autowired
    private SettingsService settingsService;
    @Autowired
    private AuctionPhotoRepository auctionPhotoRepository;

    @Autowired
    private Storage storage;

    public Auction saveAuction(Auction auction){
        return auctionRepository.save(auction);
    }

    public BidHistory saveBid_history(BidHistory bid_history){
        return bid_historyRepository.save(bid_history);
    }
    public boolean haveAmount(BidHistory bid_history, AppUserFullBalance v_app_user) throws Exception {
        if((v_app_user.getUsableBalance() < bid_history.getAmount()) || (bid_history.getAmount() < bid_history.getAuction().getStartingPrice()) ) throw new Exception("your account balance is not valid");
        return true;
    }

    public boolean haveBid_step(BidHistory bid_history) {
        return ObjectUtils.isEmpty(bid_history.getAuction().getBidStep());

    }

    public boolean verifyAmountInBid_step(BidHistory bid_history) throws Exception {
        if(bid_history.getAmount() % bid_history.getAuction().getBidStep()!=0) throw new Exception("Your amount does not follow the bid-step");
        return true;
    }

    public boolean verifyAuction(BidHistory bid_history) throws Exception {
        if(bid_history.getAuction().getEndDate().compareTo(bid_history.getDate()) < 0 && this.isUserAuction(bid_history.getAuction().getId(), bid_history.getAuction().getAppUser().getId())) throw new Exception("The Auction is no longer available");
        return true;
    }


    public Auction findById(Long id){
        return auctionRepository.findById(id).get();
    }

    public BidHistory getSecondToLastBid(Long id){
        if(bid_historyRepository.getSecondToLastBid(id).isPresent())return bid_historyRepository.getSecondToLastBid(id).get();
        return null;
    }

    public AppUserFullBalance getAppUserBalance(Long id){
        Optional<AppUserFullBalance> vap = appUserFullBalanceRepository.getAppUSerBalance(id);
        return vap.orElse(null);
    }

    public List<BidHistory> getAuctionNotClosed(){
        return bid_historyRepository.getAuctionNotClosed().orElse(null);
    }

//    public void closedAuction(){
//        List<BidHistory> auction = this.getAuctionNotClosed();
//        if(auction!=null){
//            for (BidHistory bid_history : auction) {
//                Auction au = bid_history.getAuction();
//                au.setAuctionState(auctionStateRepository.findById(2L).get());
//                AppUser appUser = bid_history.getAppUser();
//                V_app_user vAppUser = v_app_userRepository.getV_app_user(appUser.getId()).get();
//                appUser.setAccountBalance(vAppUser.getMoney_can_use());
//                app_userRepository.save(appUser);
//                auctionRepository.save(au);
//            }
//        }
//    }

    public BidHistory getTopBid(Long auctionId){
        return bid_historyRepository.findFirstByAuctionIdOrderByDateDesc(auctionId).orElse(null);
    }

    public BidHistory getLastBidUser(Long id,Long auctionId){
        return bid_historyRepository.getLastMise(id,auctionId).orElse(null);
    }
    public List<BidHistory> getBidHistory(Long auctionId) {
        return bid_historyRepository.findByAuctionIdOrderByDateDesc(auctionId).orElse(null);
    }
    public boolean isUserAuction(Long userid,Long auctionid){
        Auction auction = auctionRepository.isUserAuction(userid, auctionid).orElse(null);
        return auction == null;

    }

    @SuppressWarnings("unchecked")
    public void fillAcutions(Object auctions) {
        if(auctions instanceof List){
            List<AuctionWithState> auctionWithStates = (List<AuctionWithState>) auctions;
            for (AuctionWithState auctionWithState : auctionWithStates) {
                auctionWithState.setTopBid(this.getTopBid(auctionWithState.getId()));
                auctionWithState.setHistory(this.getBidHistory(auctionWithState.getId()));
                auctionWithState.setImages(this.getPhotos(auctionWithState.getId()));
            }
            auctionWithStates.sort((a1, a2) -> a2.getStartDate().compareTo(a1.getStartDate()));
        }else{
            AuctionWithState auctionWithState = (AuctionWithState) auctions;
            auctionWithState.setTopBid(this.getTopBid(auctionWithState.getId()));
            auctionWithState.setHistory(this.getBidHistory(auctionWithState.getId()));
            auctionWithState.setImages(this.getPhotos(auctionWithState.getId()));
        }
    }

    @SuppressWarnings("unchecked")
    public void fillLastMise(Object auctions,Long id) {
        if(auctions instanceof List){
            List<AuctionWithState> auctionWithStates = (List<AuctionWithState>) auctions;
            for (AuctionWithState auctionWithState : auctionWithStates) {
                auctionWithState.setTopBid(this.getLastBidUser(id, auctionWithState.getId()));
                auctionWithState.setHistory(this.getBidHistory(auctionWithState.getId()));
                auctionWithState.setImages(this.getPhotos(auctionWithState.getId()));
            }
            auctionWithStates.sort((a1, a2) -> a2.getStartDate().compareTo(a1.getStartDate()));
        }else{
            AuctionWithState auctionWithState = (AuctionWithState) auctions;
            auctionWithState.setTopBid(this.getLastBidUser(id, auctionWithState.getId()));
            auctionWithState.setHistory(this.getBidHistory(auctionWithState.getId()));
            auctionWithState.setImages(this.getPhotos(auctionWithState.getId()));
        }
    }

    private List<AuctionPhoto> getPhotos(Long id) {
        return auctionPhotoRepository.findByAuctionId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Response> createAuction(AuctionInput auction, String token) throws InvalidValueException {
        Optional<Category> category = categoryRepository.findById(auction.getCategoryId());
        if(category.isEmpty()) {
            throw new InvalidValueException("La catégorie rensignée n'existe pas");
        }
        AppUserToken appUserToken = appUserTokenRepository.findByValue(token.split(" ")[1]).orElse(null);
    /// auction duration
        Settings durationSettings = settingsRepository.findById(2L).orElse(null);
        settingsService.fillSettings(durationSettings);
        SettingsValueHistory duration = durationSettings.getCurrentValue();
    /// commision
        Settings commissionSettings = settingsRepository.findById(6L).orElse(null);
        settingsService.fillSettings(commissionSettings);
        SettingsValueHistory commission = commissionSettings.getCurrentValue();

        LocalDateTime endDate = auction.getStartDate().plusMinutes(Integer.parseInt(duration.getValue()));
        Auction toSave = new Auction();
        toSave.setTitle(auction.getTitle());
        toSave.setDescription(auction.getDescription());
        toSave.setStartingPrice(auction.getStartingPrice());
        toSave.setBidStep(auction.getBidStep());
        toSave.setStartDate(auction.getStartDate());
        toSave.setEndDate(endDate);
        toSave.setAppUser(appUserToken.getUser());
        toSave.setCategory(category.get());
        toSave.setCommissionRate(commission);
        Auction result = auctionRepository.save(toSave);
        List<String> paths = this.savePicture(auction.getImages());
        for(String path : paths){
            AuctionPhoto auctionPhoto = new AuctionPhoto();
            auctionPhoto.setAuctionId(result.getId());
            auctionPhoto.setPhotoPath(path);
            auctionPhotoRepository.save(auctionPhoto);
        }
        Response response = new Response(result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private List<String> savePicture(List<Photo> photos) throws InvalidValueException {
        List<String> paths = new ArrayList<>();
        for(Photo photo : photos){
            byte[] decodedBytes = Base64.getDecoder().decode(photo.getBase64String());
            Tika tika = new Tika();
            String mimeType = tika.detect(decodedBytes).split("/")[0];
            if(!mimeType.equals("image")){
                throw new InvalidValueException("Le fichier envoyé n'est pas une image");
            }
            String fileName = "images/"+DigestUtils.sha1Hex(LocalDateTime.now().toString()) + "." + photo.getFormat();
            BlobId blobId = BlobId.of("enchereapp",fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/"+photo.getFormat()).build();
            Blob blob = storage.create(blobInfo, decodedBytes);
            System.out.println(blob.getMediaLink());
            paths.add(blob.getMediaLink());
        }
        return paths;
    }
    public String getLastWord(String s){
        int lastSpace = s.lastIndexOf(" ");
        return s.substring(lastSpace+1);
    }

    public String search(List<Category> category, List<AppUser> username, List<AuctionState> status, Float min, Float max, Date startDate1, Date startDate2, Date endDate1, Date endDate2, String description){
       String req = "select * from v_auction_with_state";
        if(!category.isEmpty()){
            req +=" where (category_id = "+category.get(0).getId();
            for(int i = 1; i < category.size();i++){
                req +=" or category_id = "+category.get(i).getId();
            }
            req +=")";
        }
        if(!username.isEmpty()){
            if(this.getLastWord(req).compareTo("v_auction_with_state") ==0){
                req +=" where (app_userid ="+username.get(0).getId();
                for(int i = 1; i < username.size();i++){
                    req +=" or app_userid ="+username.get(i).getId();
                }
                req +=")";
            }
            else{
                req +=" AND (app_userid ="+username.get(0).getId();
                for(int i = 1; i < username.size();i++){
                    req +=" or app_userid ="+username.get(i).getId();
                }
                req +=")";
            }
        }
        if(!status.isEmpty()){
            if(this.getLastWord(req).compareTo("v_auction_with_state") ==0){
                req +=" where (auction_stateid = "+status.get(0).getId();
                for(int i = 1; i < status.size();i++){
                    req +=" or auction_stateid = "+status.get(i).getId();
                }
                req +=")";
            }
            else{
                req +=" AND (auction_stateid = "+status.get(0).getId();
                for(int i = 1; i < status.size();i++){
                    req +=" or auction_stateid = "+status.get(i).getId();
                }
                req +=")";
            }
        }
        if(min!=null){
            if(this.getLastWord(req).compareTo("v_auction_with_state") == 0){
                req +=" where (starting_price >= "+min;
                if(max == null)
                    req +=")";
            }
            else{
                req +=" and (starting_price >= "+min;
                if(max == null)
                    req +=")";
            }
        }
        if(max!=null){
            if(this.getLastWord(req).compareTo("v_auction_with_state") == 0){
                req +=" where (starting_price <= "+max+")";

            }
            else{
                if(min == null){
                    req +=" AND (starting_price <= "+max;
                }
                else{
                    req +=" AND starting_price <= "+max;
                }
                req +=")";
            }
        }
        if(startDate1!=null){
            if(this.getLastWord(req).compareTo("v_auction_with_state") == 0){
                req +=" where (start_date >= "+startDate1;
                if(startDate2 == null)
                    req +=")";
            }
            else{
                req +=" AND (start_date >= "+startDate1;
                if(startDate2 == null)
                    req +=")";
            }
        }
        if(startDate2!=null){
            if(this.getLastWord(req).compareTo("v_auction_with_state") == 0){
                req +=" where (start_date <= "+startDate2+")";

            }
            else{
                if(startDate1 == null){
                    req +=" AND (start_date <= "+startDate2;
                }
                else{
                    req +=" AND start_date <= "+startDate2;
                }
                req +=")";
            }
        }
        if(endDate1!=null){
            if(this.getLastWord(req).compareTo("v_auction_with_state") == 0){
                req +=" where (end_date >= "+endDate1;
                if(endDate2 == null)
                    req +=")";
            }
            else{
                req +=" AND (end_date >= "+endDate1;
                if(endDate2 == null)
                    req +=")";
            }
        }
        if(endDate2!=null){
            if(this.getLastWord(req).compareTo("v_auction_with_state") == 0){
                req +=" where (end_date <= "+endDate2+")";

            }
            else{
                if(endDate1 == null){
                    req +=" AND (end_date <= "+endDate2;
                }
                else{
                    req +=" AND end_date <= "+endDate2;
                }
                req +=")";
            }
        }
        if(description!=""){
            if(this.getLastWord(req).compareTo("auction") == 0){
                req +=" where (description LIKE '%"+description+"%')";

            }else{
                req +=" AND (description LIKE '%"+description+"%')";
            }
        }
        return req;
    }
    public List<AuctionState> getAllAuctionState(){
        return auctionStateRepository.findAll();
    }

    public List<AuctionWithState> getAuctionListDesc(Long id){
        return auctionWithStateRepository.getAuctionListDesc(id).orElse(null);
    }

    public Integer count(Long id){
        return bid_historyRepository.miseCount(id);
    }



    public String auctionMisedRequest(Long id){
        String req = "select a.id,a.description,a.title,a.category_id,a.end_date,p.max_mise,p.mise from auction a,(select max(b.amount) as max_mise ,u.user_amount as mise,b.auction_id as id from bid_history b,(select max(amount) as user_amount,auction_id from bid_history where app_user_id = "+id+" group by auction_id) as u where b.auction_id = u.auction_id group by b.auction_id,u.user_amount) as p where a.id = p.id";

        return req;
    }

    public ResponseEntity<?> getAuctionsWithState(int offset) {
        List<AuctionWithState> auctions = auctionWithStateRepository.findAllByOrderByEndDateDesc(PageRequest.of(offset,3)).get().collect(Collectors.toList());
        for (AuctionWithState auctionWithState : auctions) {
            auctionWithState.setTopBid(this.getTopBid(auctionWithState.getId()));
            auctionWithState.setImages(this.getPhotos(auctionWithState.getId()));
        }
        return ResponseEntity.ok(new Response(auctions));
    }
}
