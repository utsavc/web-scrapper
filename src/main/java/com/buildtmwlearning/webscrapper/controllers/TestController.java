package com.buildtmwlearning.webscrapper.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/hello")
    public String test(){
        System.out.println("Hello");
        return "Hello World";
    }


}
