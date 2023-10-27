package com.buildtmwlearning.webscrapper.controllers;

import com.buildtmwlearning.webscrapper.entities.Scrapper;
import com.buildtmwlearning.webscrapper.service.ScrapperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

import static com.buildtmwlearning.webscrapper.constants.WebScrapperConstants.*;

@Api(value = "ScrapperController", description = "This particular Controller is related to Scrapper Component")
@RestController
public class ScrapperController {
    private ScrapperService scrapperService;
    private Scrapper scrapper;


    @Autowired
    public ScrapperController(ScrapperService scrapperService){
        this.scrapperService=scrapperService;
    }

    @ApiOperation(value = SCRAP_SWAGGER_VALUE, response = Scrapper.class, tags = "SCRAP DATA")
    @GetMapping(value = "/scrap-data")
    public String scrapData()  {

        try {
            scrapperService.storeList();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SUCCESS_MESSAGE;
    }





    @ApiOperation(value = RETRIEVE_SWAGGER_VALUE, response = Scrapper.class, tags = "Retrieve the List")
    @GetMapping(value = "/retrieve")
    public List<Scrapper> getScrapper(){
            System.out.println(scrapperService.giveScrapper());
            return scrapperService.giveScrapper();
    }



    @ApiOperation(value = CREATECSV_SWAGGER_VALUE, response = Scrapper.class, tags = "Creates CSV")
    @GetMapping("/createcsv")
    public String makeCSV() throws Exception {
        if (scrapperService.createCSV()){
            return SUCCESS_MESSAGE;
        }else{
            return FAIL_MESSAGE;
        }
    }
}
