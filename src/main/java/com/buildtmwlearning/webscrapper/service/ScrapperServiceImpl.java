package com.buildtmwlearning.webscrapper.service;

import com.buildtmwlearning.webscrapper.entities.Scrapper;
import com.buildtmwlearning.webscrapper.constants.WebScrapperConstants;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.buildtmwlearning.webscrapper.constants.WebScrapperConstants.*;

@Service
public class ScrapperServiceImpl implements  ScrapperService {

    @Autowired
    private  ScrapperService scrapperService;

    private Configuration configuration;
    private Properties properties;

    List<Scrapper> scraps = new ArrayList<>();
    List<String> list = new ArrayList<>();
    Scrapper scrapper = new Scrapper();

    public String storeList() throws IllegalAccessException, IOException {


        configuration = new Configuration();
        properties = new Properties();
//load properties file
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Scrapper.class);
// build session factory
        SessionFactory factory = configuration.buildSessionFactory();
// get session
        Session session = factory.openSession();


        Transaction transaction = session.beginTransaction();


        Class<Scrapper> scrapperClass = Scrapper.class;
        int count=0;
        for (Field field:scrapperClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(scrapper)==null){
                count++;
            }
        }

        Document doc = Jsoup.connect(URL).get();
        Elements el = doc.select(TAG_SELECTOR);

        list = new ArrayList<>();
        List<String > ignoreValue = new ArrayList<>();
        ignoreValue.add("Ext Hours Ranking");
        ignoreValue.add("Top Gainers");
        ignoreValue.add("Top Losers");
        ignoreValue.add("Most Active");
        ignoreValue.add("MARKET");
        ignoreValue.add("");

        for (Element e : el) {
            //Finding out if the content is in the retrieved list
            if((e.toString().contains(CSS_CLASS_SELECTOR)
                    && !ignoreValue.contains(e.text().toString()))){
                list.add(e.text());
            }
        }


        int size = list.size();
        int id=0;
        for (int i = 0; i < size; i += 7) {
            Scrapper scdata =new Scrapper();
            //scdata.setName(list.get(i));
            scdata.setLastPrice(list.get(i+1));
            scdata.setChange( list.get(i + 2));
            scdata.setPercentChange(list.get(i + 3));
            scdata.setVolume(list.get(i + 4));
            scdata.setPercentTurnover(list.get(i + 5));
            scdata.setMarketCap(list.get(i + 6));

            scraps.add(scdata);
            session.save(scdata);
        }
        transaction.commit();
        // close session

        session.close();
        System.out.println(session.isConnected());

        return WebScrapperConstants.SUCCESS_MESSAGE;
    }



    public List<Scrapper> giveScrapper() {
        return this.scraps;
    }



    public boolean createCSV() throws Exception {

        return new CsvUtils().createCsv(scraps);
    }











}