package com.d4c.www.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterceptorController {

    @GetMapping("/api/v0/interceptor")
    public Object interceptor(){
        return "interceptor测试成功";
    }
    @GetMapping("/api/v0/noInterceptor")
    public Object onInterceptor(){
        return "noInterceptor测试成功";
    }
}
