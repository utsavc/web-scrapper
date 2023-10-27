package com.buildtmwlearning.webscrapper.service;

import com.buildtmwlearning.webscrapper.entities.Scrapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.buildtmwlearning.webscrapper.constants.WebScrapperConstants.SUCCESS_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScrapperServiceImplTest {

    @InjectMocks
    ScrapperServiceImpl scrapperServiceImpl;

    Scrapper scrapper;

    @Mock
    CsvUtils csvUtils;


    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void storeList() throws IllegalAccessException, IOException {
        String str=scrapperServiceImpl.storeList();
        assertEquals(SUCCESS_MESSAGE,str);
    }


   @Test
    void giveScrapper() {

        List<Scrapper> scraps =new ArrayList<>();
        /*scrapper=new Scrapper("Bt Brands","1.550",
                "-0.940","-37.75%",
                "87.80K","1.36%","9.99M");
        scraps.add(scrapper);*/
       int s=0;
       List<Scrapper> scrappers = scrapperServiceImpl.giveScrapper();
        assertEquals(s,scrappers.size());
    }


    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    void createCSV() throws Exception {
        List<Scrapper> scraps =new ArrayList<>();
        scrapper=new Scrapper();
        //scrapper.setName("A");
        scrapper.setLastPrice("B");
        scrapper.setChange( "C");
        scrapper.setPercentChange("D");
        scrapper.setVolume("E");
        scrapper.setPercentTurnover("F");
        scrapper.setMarketCap("G");
        scraps.add(scrapper);
        when(csvUtils.createCsv(scraps)).thenReturn(true);
        boolean b=scrapperServiceImpl.createCSV();
        assertEquals(true,b);

    }



}