package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.DailyAuction;
import mg.cloud.enchere_back_end.Repository.DailyAuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatistiqueService {
    @Autowired
    private DailyAuctionRepository dailyAuctionRepository;

    public List<DailyAuction> getDailySales(){
        return dailyAuctionRepository.getDailySales().orElse(null);
    }

    public Integer getTotalSales(){
        return dailyAuctionRepository.getTotalSales().orElse(0);
    }

    public Integer getCommisionAverage(){
        return dailyAuctionRepository.getCommisionAverage().orElse(null);
    }

    public List<DailyAuction> getAuctionFinished(){
        return dailyAuctionRepository.getAuctionFinished().orElse(null);
    }

    public List<DailyAuction> getAuctionStarted(){
        return dailyAuctionRepository.getAuctionStarted().orElse(null);
    }

    public Integer getLeastValuableAuction(){
        return dailyAuctionRepository.getLeastValuableAuction().orElse(0);
    }

    public Integer getMostValuableAuction(){
        return dailyAuctionRepository.getMostValuableAuction().orElse(0);
    }
}
