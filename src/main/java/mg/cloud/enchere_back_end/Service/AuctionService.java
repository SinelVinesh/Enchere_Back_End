package mg.cloud.enchere_back_end.Service;

import jakarta.servlet.ServletContext;
import mg.cloud.enchere_back_end.Model.*;
import mg.cloud.enchere_back_end.Repository.*;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import mg.cloud.enchere_back_end.inputs.AuctionInput;
import mg.cloud.enchere_back_end.inputs.Photo;
import mg.cloud.enchere_back_end.response.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
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


    public Auction saveAuction(Auction auction){
        return auctionRepository.save(auction);
    }

    public BidHistory saveBid_history(BidHistory bid_history){
        return bid_historyRepository.save(bid_history);
    }
    public boolean haveAmount(BidHistory bid_history, V_app_user v_app_user) throws Exception {
        if((v_app_user.getMoney_can_use() < bid_history.getAmount()) || (bid_history.getAmount() < bid_history.getAuction().getStartingPrice()) ) throw new Exception("your account balance is not valid");
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
        if(bid_history.getAuction().getEndDate().compareTo(bid_history.getDate()) < 0) throw new Exception("The Auction is no longer available");
        return true;
    }


    public Auction findById(Long id){
        return auctionRepository.findById(id).get();
    }

    public BidHistory getSecondToLastBid(Long id){
        if(bid_historyRepository.getSecondToLastBid(id).isPresent())return bid_historyRepository.getSecondToLastBid(id).get();
        return null;
    }

    public V_app_user getV_app_user(Long id){
       Optional<V_app_user> vap = v_app_userRepository.getV_app_user(id);
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

    public List<BidHistory> getBidHistory(Long auctionId) {
        return bid_historyRepository.findByAuctionIdOrderByDateDesc(auctionId).orElse(null);
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
        }else{
            AuctionWithState auctionWithState = (AuctionWithState) auctions;
            auctionWithState.setTopBid(this.getTopBid(auctionWithState.getId()));
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

        LocalDateTime endDate = auction.getStartDate().plusHours(Integer.parseInt(duration.getValue()));
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
            String fileName = "/users/" + DigestUtils.sha1Hex(LocalDateTime.now().toString()) + "." + photo.getFormat();
            String completePath = getClass().getClassLoader().getResource(".").getFile() +"static";
            byte[] decodedBytes = Base64.getDecoder().decode(photo.getBase64String());
            Tika tika = new Tika();
            String mimeType = tika.detect(decodedBytes).split("/")[0];
            if(!mimeType.equals("image")){
                throw new InvalidValueException("Le fichier envoyé n'est pas une image");
            }
            try {
                File file = new File(completePath+fileName);
                if(!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if(file.createNewFile()){
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(decodedBytes);
                    fos.close();
                    paths.add(fileName);
                }
                throw new IOException("Impossible de créer le fichier");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return paths;
    }
}
