package com.d4c.www.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping( value = "/test/a")
    public String a(){
        return "SASASA";
    }
}
