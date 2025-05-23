package com.d4c.controller;

import com.d4c.service.ProductService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** *@author JoeZhou */
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {  
  
    @DubboReference(version = "1.0.0", loadbalance = "roundrobin")
    private ProductService productService;
      
    @GetMapping("/getById/{id}")
    public String getById(@PathVariable Long id) {
        // 查询订单中的商品名称：假设1号订单购买了1号商品，2号订单中购买了2号商品  
        Long productId = id == 1L ? 1L : 2L;  
        // 远程调用商品微服务中的对应方法  
        String productName = productService.getById(productId);  
        return id == 1L ? "1号订单：" + productName : "2号订单：" + productName;  
    }  
}