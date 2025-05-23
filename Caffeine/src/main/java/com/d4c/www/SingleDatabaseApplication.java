package com.d4c.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SingleDatabaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(SingleDatabaseApplication.class, args);
    }
}