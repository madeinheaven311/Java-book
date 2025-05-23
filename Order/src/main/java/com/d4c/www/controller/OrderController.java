package com.d4c.www.controller;

import com.d4c.www.feignClient.ProductFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    ProductFeign productFeign;
    @GetMapping("/test/{id}")
    public String orderTest(@PathVariable Long id){

        return productFeign.getById(id);
    }
}
