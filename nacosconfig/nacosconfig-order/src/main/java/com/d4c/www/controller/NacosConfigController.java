package com.d4c.www.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/api/v1/nacosconfig")
public class NacosConfigController {

    
    @Value("${project.env}")
    private String env;

    @GetMapping("/getEnv")
    public String getEnv() {
        return "NacosConfig中的project.env值" + env;
    }
}