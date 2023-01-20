CREATE OR REPLACE VIEW V_APP_USER AS (
  SELECT (ROW_NUMBER() OVER (ORDER BY B.APP_USERID))::INTEGER AS ID,
  B.APP_USERID AS APP_USERID,
  (AU.ACCOUNT_BALANCE - SUM(B.AMOUNT)) AS MONEY_CAN_USE FROM BID_HISTORY B JOIN APP_USER AU ON B.APP_USERID = AU.ID JOIN AUCTION A on a.id = b.bidid WHERE a.auction_stateid = 1 and B.ID IN(SELECT MAX(B.ID) FROM BID_HISTORY B GROUP BY B.BIDID) GROUP BY B.APP_USERID,
  AU.ACCOUNT_BALANCE
);

SELECT
(ROW_NUMBER() OVER (ORDER BY DATE(A.END_DATE)))::INTEGER AS ID,
  DATE(A.END_DATE) AS "Date",
  SUM(B.AMOUNT)    AS AMOUNT
FROM
  BID_HISTORY B
  JOIN AUCTION A
  ON A.ID = B.BIDID
WHERE
  DATE(A.END_DATE) <= NOW()
  AND B.ID IN (
    SELECT
      MAX(B.ID)
    FROM
      BID_HISTORY B
    GROUP BY
      B.BIDID
  )

GROUP BY
  "Date";

  SELECT
      Count(a.ID)
    FROM
      Auction a WHERE
  DATE(A.END_DATE) <= NOW() and a.starting_price <20000

select AVG(c.amount) from commision c join auction a on a.id = c.auctionid where  DATE(A.END_DATE) <= NOW()

SELECT
      (ROW_NUMBER() OVER (ORDER BY DATE(A.END_DATE))) AS ID,DATE(A.END_DATE),count(DATE(A.END_DATE)) as amount
    FROM
      Auction a WHERE
  DATE(A.END_DATE) > NOW() GROUP BY  DATE(A.END_DATE)


   
 select * from app_user_recharge_state_history where app_user_recharge_requestid not in(select app_user_recharge_requestid from app_user_recharge_state_history where recharge_stateid != 1);

 SELECT
      a.ID
    FROM
      Auction a WHERE
  DATE(A.END_DATE) <= NOW() and a.starting_price <20000


select a.* from auction a join bid_history b on b.bidid = a.id where  A.END_DATE <= NOW() and b.id in (SELECT MAX(B.ID) FROM BID_HISTORY B GROUP BY B.BIDID)order by amount;

select a.* from auction a join bid_history b on b.bidid = a.id where b.id in (SELECT MAX(B.ID) FROM BID_HISTORY B GROUP BY B.BIDID)order by b.amount desc limit 1;

SELECT MAX(B.ID) FROM BID_HISTORY B GROUP BY B.BIDID

select b.* from auction a join bid_history b on b.bidid = a.id where a.END_DATE <= NOW() and a.auction_stateid = 1 and b.id in (SELECT MAX(B.ID) FROM BID_HISTORY B GROUP BY B.BIDID);