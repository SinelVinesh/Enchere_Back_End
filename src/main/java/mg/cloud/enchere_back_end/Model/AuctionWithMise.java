package mg.cloud.enchere_back_end.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AuctionWithMise {

    private Long id;


    private String description;


    private String title;


    private Long categoryId;

    private Timestamp end_date;


    private Float starting_price;



    private Integer max_mise;


    private Float mise;

}
