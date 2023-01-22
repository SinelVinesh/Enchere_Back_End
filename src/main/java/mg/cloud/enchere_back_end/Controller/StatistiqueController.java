package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Model.*;
import mg.cloud.enchere_back_end.response.Response;
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

    @GetMapping("/turnover")
    public ResponseEntity<?> getDailySales(){
        List<DailyTurnover> dailyTurnovers = statistiqueService.getDailyTurnover();
        Float totalTurnover = dailyTurnovers.stream().map(DailyTurnover::getTurnover).reduce(0f, Float::sum);
        HashMap<String,Object> responseData = new HashMap<>();
        responseData.put("dailySales", dailyTurnovers);
        responseData.put("totalSales",totalTurnover);
        responseData.put("commisionAverage",statistiqueService.getCommisionAverage());
        return new ResponseEntity<>(new Response(responseData), HttpStatus.OK);
    }

    @GetMapping("/auctions")
    public ResponseEntity<?> getAuctionStatistics(){
        List<AuctionStat> auctionStats = statistiqueService.getAuctionStats();
        AuctionWithTopBid leastValuableAuction = statistiqueService.getLeastValuableAuction();
        AuctionWithTopBid mostValuableAuction = statistiqueService.getMostValuableAuction();
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("dailyAuctionStat", auctionStats);
        responseData.put("totalAuctionFinished", statistiqueService.getTotalSales());
        responseData.put("leastValuableAuction", leastValuableAuction);
        responseData.put("mostValuableAuction", mostValuableAuction);

        return new ResponseEntity<>(new Response(responseData), HttpStatus.OK);
    }
}
