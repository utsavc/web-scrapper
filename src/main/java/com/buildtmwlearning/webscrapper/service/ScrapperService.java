package com.buildtmwlearning.webscrapper.service;

import com.buildtmwlearning.webscrapper.entities.Scrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ScrapperService {
    public String storeList() throws IllegalAccessException, IOException;
    public boolean createCSV() throws Exception;
    public List<Scrapper> giveScrapper();

}
