package com.buildtmwlearning.webscrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.buildtmwlearning.webscrapper")
public class WebScrapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebScrapperApplication.class, args);
    }

}
