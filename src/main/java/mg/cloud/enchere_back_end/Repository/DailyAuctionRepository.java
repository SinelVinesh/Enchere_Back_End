package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.DailyAuction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DailyAuctionRepository extends JpaRepository<DailyAuction,Long> {
    @Query(value = "SELECT\n" +
            "(ROW_NUMBER() OVER (ORDER BY DATE(A.END_DATE))) AS ID,\n" +
            "  DATE(A.END_DATE) AS \"Date\",\n" +
            "  Count(DATE(A.END_DATE))    AS AMOUNT\n" +
            "FROM\n" +
            "  BID_HISTORY B\n" +
            "  JOIN AUCTION A\n" +
            "  ON A.ID = B.BIDID\n" +
            "WHERE\n" +
            "  DATE(A.END_DATE) <= NOW()\n" +
            "  AND B.ID IN (\n" +
            "    SELECT\n" +
            "      MAX(B.ID)\n" +
            "    FROM\n" +
            "      BID_HISTORY B\n" +
            "    GROUP BY\n" +
            "      B.BIDID\n" +
            "  )\n" +
            "GROUP BY\n" +
            "  \"Date\"",nativeQuery = true)
    Optional<List<DailyAuction>> getDailySales();

    @Query(value = "SELECT\n" +
            "      Count(a.ID)\n" +
            "    FROM\n" +
            "      Auction a WHERE\n" +
            "  DATE(A.END_DATE) <= NOW()",nativeQuery = true)
    Optional<Integer> getTotalSales();

    @Query(value = "select AVG(c.amount) from commision c join auction a on a.id = c.auctionid where  DATE(A.END_DATE) <= NOW()",nativeQuery = true)
    Optional<Integer> getCommisionAverage();

    @Query(value = "SELECT\n" +
            "      (ROW_NUMBER() OVER (ORDER BY DATE(A.END_DATE))) AS ID,DATE(A.END_DATE),count(DATE(A.END_DATE)) as amount\n" +
            "    FROM\n" +
            "      Auction a WHERE\n" +
            "  DATE(A.END_DATE) <= NOW() GROUP BY  DATE(A.END_DATE)",nativeQuery = true)
    Optional<List<DailyAuction>>  getAuctionFinished();

    @Query(value = "SELECT\n" +
            "      (ROW_NUMBER() OVER (ORDER BY DATE(A.END_DATE))) AS ID,DATE(A.END_DATE),count(DATE(A.END_DATE)) as amount\n" +
            "    FROM\n" +
            "      Auction a WHERE\n" +
            "  DATE(A.END_DATE) > NOW() GROUP BY  DATE(A.END_DATE)",nativeQuery = true)
    Optional<List<DailyAuction>>  getAuctionStarted();

    @Query(value = "SELECT\n" +
            "      Count(a.ID)\n" +
            "    FROM\n" +
            "      Auction a WHERE\n" +
            "  DATE(A.END_DATE) <= NOW() and a.starting_price < 20000",nativeQuery = true)
    Optional<Integer> getLeastValuableAuction();

    @Query(value = "SELECT\n" +
            "      Count(a.ID)\n" +
            "    FROM\n" +
            "      Auction a WHERE\n" +
            "  DATE(A.END_DATE) <= NOW() and a.starting_price >= 200000",nativeQuery = true)
    Optional<Integer> getMostValuableAuction();
}