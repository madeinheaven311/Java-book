package com.d4c.www.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AopServiceImpl {

    public String test(String param) {
        return param;
    }
}
