package com.d4c.www.controller;

import com.d4c.www.entity.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StartController {

    @GetMapping("/test/start")
    public Object start() {
        Map<String, String> hashmap = new HashMap<>();
        hashmap.put("name","赵四");
        return hashmap;
    }

    @GetMapping("/test/start1")
    public Object start1( ) {
        Map<String, String> hashmap = new HashMap<>();
        hashmap.put("name","赵四");
        return new Result(hashmap);
    }


    //测试全局异常，统一异常的前端返回页面，交于切面处理类处理（切面类中写入异常处理程序），而不用自带的异常
    @GetMapping("/test/exception/{num}")
    public Object exception(@PathVariable Integer num) {
        if (num == 0) {
            throw new RuntimeException("除数不能为0");
        }
        return Map.of("name", "zhaosi");
    }
}