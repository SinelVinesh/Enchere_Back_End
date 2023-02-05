package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.*;
import mg.cloud.enchere_back_end.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatistiqueService {
    @Autowired
    private DailyAuctionRepository dailyAuctionRepository;
    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private DailyTurnoverRepository dailyTurnoverRepository;
    @Autowired
    private AuctionWithTopBidRepository auctionWithTopBidRepository;
    @Autowired
    private AuctionStatRepository auctionStatRepository;

    public List<DailyTurnover> getDailyTurnover(){
        return dailyTurnoverRepository.findAll();
    }

    public Integer getTotalSales(){
        return dailyAuctionRepository.getTotalSales().orElse(0);
    }

    public Float getCommisionAverage(){
        List<AuctionWithTopBid> auctionWithTopBids = auctionWithTopBidRepository.findAll();
        Float commisionTotal = auctionWithTopBids.stream().map(AuctionWithTopBid::getCommissionAmount).reduce(0f, Float::sum);
        return commisionTotal/auctionWithTopBids.stream().filter(auctionWithTopBid -> auctionWithTopBid.getEndDate().isBefore(LocalDateTime.now())).count();
    }
    public List<AuctionStat> getAuctionStats() {
        return auctionStatRepository.findAll();
    }

    public AuctionWithTopBid getLeastValuableAuction(){
        return auctionWithTopBidRepository.findFirstByOrderByTopBidAmountAsc();
    }
    public AuctionWithTopBid getMostValuableAuction(){
        return auctionWithTopBidRepository.findFirstByOrderByTopBidAmountDesc();
    }
}
