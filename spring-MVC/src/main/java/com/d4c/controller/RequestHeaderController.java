package com.d4c.controller;


import com.d4c.entity.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("/api/v1/requestHeader")
public class RequestHeaderController {

    @GetMapping("/getHost")
    public Result getHost(@RequestHeader("host") String host){
        return new Result(Map.of("请求头参数host", host));
    }




}
