package com.d4c.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api/v1/forward")
public class ForwardController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test(){

        return "forward-test";

    }
}
