package com.d4c.www.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/goods")
public class OrderController {

    @GetMapping("/goods")
    public String goods(){
        return "goods";
    }
}
