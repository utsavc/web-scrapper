package com.buildtmwlearning.webscrapper.controllers;

import com.buildtmwlearning.webscrapper.entities.Scrapper;
import com.buildtmwlearning.webscrapper.service.ScrapperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.util.ArrayList;

import static com.buildtmwlearning.webscrapper.constants.WebScrapperConstants.FAIL_MESSAGE;
import static com.buildtmwlearning.webscrapper.constants.WebScrapperConstants.SUCCESS_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScrapperControllerTest {

    @Spy
    @InjectMocks
    ScrapperController scrapperController;

    @Mock
    ScrapperService scrapperService;

    Scrapper scrapper;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }



    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    void scrapDataTest() throws IllegalAccessException, IOException {

        when(scrapperService.storeList()).thenReturn("Scrapping Successful");
        String str=scrapperController.scrapData();
        assertEquals(SUCCESS_MESSAGE,str);

    }


    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    void ScrapDataExceptionTest() throws IllegalAccessException, IOException {

        when(scrapperController.scrapData()).thenThrow(IllegalAccessException.class);
        scrapperController.scrapData();
        doThrow(new IllegalAccessException()).when(scrapperService).storeList();

    }


    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    void ScrapDataIOExceptionTest() throws IllegalAccessException, IOException {

        when(scrapperController.scrapData()).thenThrow(IOException.class);
        scrapperController.scrapData();
        doThrow(new IllegalAccessException()).when(scrapperService).storeList();
    }





    @Test
    void getScrapperTest() {
        ArrayList<Scrapper> s=new ArrayList<>();
        scrapper=new Scrapper();
        //scrapper.setName("A");
        scrapper.setLastPrice("B");
        scrapper.setChange( "C");
        scrapper.setPercentChange("D");
        scrapper.setVolume("E");
        scrapper.setPercentTurnover("F");
        scrapper.setMarketCap("G");
        s.add(scrapper);
        when(scrapperService.giveScrapper()).thenReturn(s);
        int actSize=scrapperController.getScrapper().size();

        int expectedsize = scrapperService.giveScrapper().size();
        assertEquals(expectedsize,actSize);
    }


    @Test
    void makeCSVTest() throws Exception {
        when(scrapperService.createCSV()).thenReturn(true);
        String s = scrapperController.makeCSV();
        assertEquals(SUCCESS_MESSAGE,s);

    }

    @Test
    void makeCSVTest2() throws Exception {
        when(scrapperService.createCSV()).thenReturn(false);
        String failMessage = scrapperController.makeCSV();
        assertEquals(FAIL_MESSAGE,failMessage);
    }
}