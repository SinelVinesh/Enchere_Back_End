package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Model.DailyAuction;
import mg.cloud.enchere_back_end.Response.ErrorResponse;
import mg.cloud.enchere_back_end.Service.StatistiqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/statistique")
public class StatistiqueController {

    private final StatistiqueService statistiqueService;

    public StatistiqueController(StatistiqueService statistiqueService) {
        this.statistiqueService = statistiqueService;
    }

    @GetMapping("/dailysales")
    public ResponseEntity<?> getDailySales(){
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
        try {
            List<DailyAuction> AuctionFinished = statistiqueService.getAuctionFinished();
            List<DailyAuction> AuctionStarted = statistiqueService.getAuctionStarted();
            HashMap<String, Object> responseData = new HashMap<>();
            responseData.put("dailyAuctionFinished", AuctionFinished);
            responseData.put("dailyAuctionStarted", AuctionStarted);
            responseData.put("totalAuctionFinished", statistiqueService.getTotalSales());
            responseData.put("leastValuableAuction", statistiqueService.getLeastValuableAuction());
            responseData.put("mostValuableAuction", statistiqueService.getMostValuableAuction());

            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        catch (Exception e){
            ErrorResponse response = new ErrorResponse(404,"no Auction statistics");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }
}
