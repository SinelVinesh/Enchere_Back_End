package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Model.Auction;
import mg.cloud.enchere_back_end.Model.DailyAuction;
import mg.cloud.enchere_back_end.Response.ErrorResponse;
import mg.cloud.enchere_back_end.Service.AuctionService;
import mg.cloud.enchere_back_end.Service.StatistiqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatistiqueController {

    private final StatistiqueService statistiqueService;
    private final AuctionService auctionService;

    public StatistiqueController(StatistiqueService statistiqueService, AuctionService auctionService) {
        this.statistiqueService = statistiqueService;
        this.auctionService = auctionService;
    }

    @GetMapping("/dailysales")
    public ResponseEntity<?> getDailySales(){
        auctionService.closedAuction();
        List<DailyAuction> dailyAuctionList = statistiqueService.getDailySales();
        HashMap<String,Object> responseData = new HashMap<>();
        responseData.put("dailySales", dailyAuctionList);
        responseData.put("totalSales",statistiqueService.getTotalSales());
        responseData.put("commisionAverage",statistiqueService.getCommisionAverage());
        if(dailyAuctionList !=null){
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else {
            ErrorResponse response = new ErrorResponse(404,"no Sales statistics");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/AuctionStatistics")
    public ResponseEntity<?> getAuctionStatistics(){
        auctionService.closedAuction();
        try {
            List<DailyAuction> AuctionFinished = statistiqueService.getAuctionFinished();
            List<DailyAuction> AuctionStarted = statistiqueService.getAuctionStarted();
            Auction leastValuableAuction = statistiqueService.getLeastValuableAuction();
            Auction mostValuableAuction = statistiqueService.getMostValuableAuction();
            HashMap<String, Object> responseData = new HashMap<>();
            responseData.put("dailyAuctionFinished", AuctionFinished);
            responseData.put("dailyAuctionStarted", AuctionStarted);
            responseData.put("totalAuctionFinished", statistiqueService.getTotalSales());
            responseData.put("leastValuableAuction", leastValuableAuction);
            responseData.put("mostValuableAuction", mostValuableAuction);

            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        catch (Exception e){
            ErrorResponse response = new ErrorResponse(404,"no Auction statistics");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }
}
