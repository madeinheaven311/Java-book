package com.d4c.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/lockBack")
public class LogBackController {


    @GetMapping("/logBack")
    public String logBack(){
        log.debug("DEBUG级别日志");
        log.info("INFO级别日志");
        log.warn("WARN级别日志");
        log.error("ERROR级别日志");
        return "success";
    }

}
