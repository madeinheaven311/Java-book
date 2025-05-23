package com.d4c.www.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {  
  
    @GetMapping("/getById/{id}")
    public String getById(@PathVariable Long id) {
        return id == 1L ? "小米手机" : "苹果手机";  
    }  
}