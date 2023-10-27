package com.buildtmwlearning.webscrapper.entities;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;


@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "data")
public class Scrapper {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @CsvBindByName(column = "Name",required = true)
    @CsvBindByPosition(position=0)
    @Column(name = "NAME")
    private String x;

    @CsvBindByName(column = "Last Price",required = true)
    @CsvBindByPosition(position=1)
    @Column(name = "LAST_PRICE")
    private String lastPrice;

    @CsvBindByName(column = "Change",required = true)
    @CsvBindByPosition(position=2)
    @Column(name = "CHANGE")
    private String change;


    @CsvBindByName(column = "Percent Change",required = true)
    @CsvBindByPosition(position=3)
    @Column(name = "PERCENT_CHANGE")
    private String percentChange;


    @CsvBindByName(column = "Volume",required = true)
    @CsvBindByPosition(position=4)
    @Column(name = "VOLUME")
    private String volume;

    @CsvBindByName(column = "Percent Turn over",required = true)
    @CsvBindByPosition(position=5)
    @Column(name = "PERCENT_TURNOVER")
    private String percentTurnover;

    @CsvBindByName(column = "Market Cap",required = true)
    @CsvBindByPosition(position=6)
    @Column(name = "MARKET_CAP")
    private String marketCap;

}
