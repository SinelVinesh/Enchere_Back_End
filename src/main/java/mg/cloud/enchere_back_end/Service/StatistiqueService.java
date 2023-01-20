package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.Auction;
import mg.cloud.enchere_back_end.Model.DailyAuction;
import mg.cloud.enchere_back_end.Repository.AuctionRepository;
import mg.cloud.enchere_back_end.Repository.DailyAuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatistiqueService {
    @Autowired
    private DailyAuctionRepository dailyAuctionRepository;
    @Autowired
    private AuctionRepository auctionRepository;

    public List<DailyAuction> getDailySales(){
        return dailyAuctionRepository.getDailySales().orElse(null);
    }

    public Integer getTotalSales(){
        return dailyAuctionRepository.getTotalSales().orElse(0);
    }

    public Integer getCommisionAverage(){
        return dailyAuctionRepository.getCommisionAverage().orElse(0);
    }

    public List<DailyAuction> getAuctionFinished(){
        return dailyAuctionRepository.getAuctionFinished().orElse(null);
    }

    public List<DailyAuction> getAuctionStarted(){
        return dailyAuctionRepository.getAuctionStarted().orElse(null);
    }

    public Auction getLeastValuableAuction(){
        return auctionRepository.getLeastValuableAuction().orElse(null);
    }
    public Auction getMostValuableAuction(){
        return auctionRepository.getMostValuableAuction().orElse(null);
    }
}
