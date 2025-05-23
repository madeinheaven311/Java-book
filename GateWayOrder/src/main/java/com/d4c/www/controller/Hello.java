package com.d4c.www.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-app/api/v1")
public class Hello {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}
