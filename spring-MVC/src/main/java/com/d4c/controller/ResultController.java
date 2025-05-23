package com.d4c.controller;


import com.d4c.entity.result.Result;
import com.d4c.entity.result.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/result/")
@RestController
public class ResultController {

    @GetMapping("result01")
    public Result result01() {
        return new Result("一名用户的信息");
    }

    @GetMapping("result02")
    public Result result02() {
        return new Result(ResultCode.FAILED);
    }

    @GetMapping("result03")
    public Result result03() {
        return new Result(ResultCode.TOKEN_EXPIRING_SOON, "新的Token");
    }

}