package mg.cloud.enchere_back_end.Controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;
import mg.cloud.enchere_back_end.Model.*;
import mg.cloud.enchere_back_end.Repository.AuctionRepository;
import mg.cloud.enchere_back_end.Repository.AuctionWithStateRepository;
import mg.cloud.enchere_back_end.Service.AppUserService;
import mg.cloud.enchere_back_end.Service.AuctionService;
import mg.cloud.enchere_back_end.Service.CrudService;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import mg.cloud.enchere_back_end.request.AuctionInput;
import mg.cloud.enchere_back_end.request.BidInput;
import mg.cloud.enchere_back_end.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class AuctionController {
    private final AuctionService auctionService;
    private final AppUserService app_userService;
    private final CrudService<Auction,Long> crudServiceAuction;
    private final CrudService<AuctionWithState, Long> crudServiceAuctionWithState;
    private final AuctionRepository auctionRepository;
    private final AuctionWithStateRepository auctionWithStateRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public AuctionController(
            AuctionService auctionService,
            AppUserService app_userService,
            CrudService<Auction, Long> crudServiceAuction,
            CrudService<AuctionWithState, Long> crudServiceAuctionWithState,
            AuctionRepository auctionRepository,
            AuctionWithStateRepository auctionWithStateRepository
    ) {
        this.auctionService = auctionService;
        this.app_userService = app_userService;
        this.crudServiceAuction = crudServiceAuction;
        this.auctionRepository = auctionRepository;
        this.auctionWithStateRepository = auctionWithStateRepository;
        this.crudServiceAuctionWithState = crudServiceAuctionWithState;
    }

    @PostMapping("auctions/bid")
    public ResponseEntity<?> bid(BidInput bidInput) {
        AppUser user = app_userService.findById(bidInput.getAppUserId());
        AppUserFullBalance v_app_user = auctionService.getAppUserBalance(user.getId());

        BidHistory bid_history = new BidHistory();
        bid_history.setAppUser(user);
        bid_history.setAuction(auctionService.findById(bidInput.getBidId()));
        bid_history.setAmount(bidInput.getAmount());
        bid_history.setDate(LocalDateTime.now());
        try{
            if(auctionService.verifyAuction(bid_history)) {
                if (auctionService.haveAmount(bid_history,v_app_user)) {
                    if(!auctionService.haveBid_step(bid_history)) {
                        if(auctionService.verifyAmountInBid_step(bid_history)){

                            app_userService.saveAppUser(user);
                            auctionService.saveBid_history(bid_history);
                        }
                    }else {

                        app_userService.saveAppUser(user);
                        auctionService.saveBid_history(bid_history);
                    }
                }
            }
        }catch (Exception e){
            Response response = new Response(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bid_history, HttpStatus.OK);
    }

    @GetMapping(value={"/auctions/{id}"})
    public ResponseEntity<Response> getAuctions(@PathVariable("id") Optional<Long> id, HttpServletRequest request){
        ResponseEntity<Response> response = crudServiceAuctionWithState.handle(request.getMethod(), auctionWithStateRepository, id, null);
        if(response.getBody() != null){
            auctionService.fillAcutions(response.getBody().getData());
        }
        return response;
    }

    @GetMapping(value={"/auctionsOffset/{offset}"})
    public ResponseEntity<?> getAuctionOffset(@PathVariable int offset) {
        return auctionService.getAuctionsWithState(offset);
    }

    @PostMapping(value={"/auctions"})
    public ResponseEntity<Response> createAuction(@RequestBody AuctionInput auction, @RequestHeader("Authorization") String token) throws InvalidValueException {
        return auctionService.createAuction(auction, token);
    }

    @RequestMapping(value={"/auctions","/auctions/{id}"})
    public ResponseEntity<Response> crudAuction(
            @PathVariable("id") Optional<Long> id,
            @RequestBody Optional<Auction> auction,
            HttpServletRequest request) {
        Auction data = auction.orElse(null);
        return crudServiceAuction.handle(request.getMethod(), auctionRepository, id, data);
    }

    @PostMapping("/auctions/search")
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> advancedSearch(
            @RequestBody AuctionParam auctionParams

    ) {
        List<Category> category =  auctionParams.getCategory();
        List<AppUser> username = auctionParams.getUsername();
        List<AuctionState> status = auctionParams.getAuctionState();
        List<Float> prix = auctionParams.getPrix();

        Date startDateMin = auctionParams.getStartDateMin();
        Date startDateMax = auctionParams.getStartDateMax();
        Date endDateMin = auctionParams.getEndDateMin();
        Date endDateMax = auctionParams.getEndDateMax();

        String req = auctionService.search(category,username,status, prix.get(0), prix.get(1),startDateMin,startDateMax,endDateMin,endDateMax,auctionParams.getDescription());
        System.out.println(req);
        Query query =  entityManager.createNativeQuery(req,AuctionWithState.class);
        List<AuctionWithState> auctionList = (List<AuctionWithState>) query.getResultList();
        auctionService.fillAcutions(auctionList);
        if(auctionList!=null){
            HashMap<String, Object> responseData = new HashMap<>();
            responseData.put("data", auctionList);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else {
            Response error = new Response("no search found");
            return new ResponseEntity<>(error, HttpStatus.OK);
        }
    }

    @GetMapping("/auctionState")
    public ResponseEntity<?> getAllAuctionState(
    ){
        List<AuctionState> auctionState = auctionService.getAllAuctionState();
        HashMap<String, Object> responseData = new HashMap<>();

        if(auctionState!=null){
            responseData.put("data",auctionState);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            Response error = new Response("Auction state is null");
            return new ResponseEntity<>(error, HttpStatus.OK);
        }

    }

    @GetMapping("/auctions/recentAuction/{id}")
    public ResponseEntity<?> recentAuction(
            @PathVariable("id") Long id
    ){
        List<AuctionWithState> auctionState = auctionService.getAuctionListDesc(id);
        auctionService.fillAcutions(auctionState);
        HashMap<String, Object> responseData = new HashMap<>();
        if(auctionState!=null){
            responseData.put("data",auctionState);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            Response error = new Response("Auction is null");
            return new ResponseEntity<>(error, HttpStatus.OK);
        }

    }

    @GetMapping("/auctions/count/{id}")
    public ResponseEntity<?> count(
            @PathVariable("id") Long id

    ){
        Integer auctionState = auctionService.count(id);
        HashMap<String, Object> responseData = new HashMap<>();

        if(auctionState!=null){
            responseData.put("data",auctionState);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            Response error = new Response("Auction is null");
            return new ResponseEntity<>(error, HttpStatus.OK);
        }

    }

    @GetMapping("/auctions/mised/{id}")
    public ResponseEntity<?> getAuctionMissed(
            @PathVariable("id") Long id

    ){
        List<AuctionWithState> auctionList = auctionService.getAuctionListDesc(id);
        auctionService.fillLastMise(auctionList,id);
        List<AuctionWithState> auctionListMissed = new ArrayList<>();
        HashMap<String, Object> responseData = new HashMap<>();
        for ( AuctionWithState auction : auctionList) {
            if(auction.getTopBid()!= null){
                auctionListMissed.add(auction);
            }
        }
        if(auctionList.isEmpty()){
            System.out.println(auctionListMissed);
            responseData.put("data",auctionListMissed);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            Response error = new Response("Auction is null");
            return new ResponseEntity<>(error, HttpStatus.OK);
        }

    }
}
