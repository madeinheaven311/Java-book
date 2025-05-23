package com.d4c.www.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("sleuth-product")
public interface ProductFeign {
    
    /**
     * 远程: 通过商品主键查询一条商品记录
     * 
     * @param id 商品主键
     * @return 一条商品记录
     */
    @GetMapping("/api/v1/product/getById/{id}")
    String getById(@PathVariable("id") Long id);
}