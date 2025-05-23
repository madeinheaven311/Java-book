package com.d4c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api/v1/redirect")
public class RedirectController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test(){
        return "redirect:/view/redirect-test.html";
    }
}
