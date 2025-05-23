package com.d4c.www.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class NginxController {

    @GetMapping("/hello")
    public Map<String, Object> hello(HttpSession httpSession) {
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("applicationName", "app-01");
        resultMap.put("port", 6625);
        resultMap.put("session-id", httpSession.getId());
        return resultMap;
    }
}