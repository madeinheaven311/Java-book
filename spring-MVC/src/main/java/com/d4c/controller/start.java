package com.d4c.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/start/")
public class start {

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public Object test1(){
        System.out.println("访问成功");
         Map<String, String> hashmap = new HashMap<>();
         hashmap.put("name","赵四");
        return hashmap;
    }


    @RequestMapping(value = "/test2", produces = "application/json;charset=utf8")
    public Object test2(){
        System.out.println("访问成功");
        return "你好";
    }

    @RequestMapping(value = "/test3",
            method = RequestMethod.GET,
            produces = "application/json;charset=utf8")
    public Object test() {
        System.out.println("test01 访问成功!");
        return "{name: 赵四, age: 18}";
    }
}
