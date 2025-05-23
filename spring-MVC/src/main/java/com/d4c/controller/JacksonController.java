package com.d4c.controller;


import com.d4c.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/jackson")
public class JacksonController {

    @GetMapping("/dto")
    public Object dto(){
        UserDTO userDTO = new UserDTO();
        userDTO.setBirthDay( new Date());
        userDTO.setPassword(123);
        userDTO.setUserId(1);
        userDTO.setUserName("赵四");
        userDTO.setIdCard(5);
        return userDTO;
    }

    @RequestMapping(value = "/map")
    public Object map(){
        System.out.println("访问成功");
        return Map.of("name","赵四","age","50");
    }
}
