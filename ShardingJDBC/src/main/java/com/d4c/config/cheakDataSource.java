package com.d4c.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
public class cheakDataSource {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void checkDataSource() {
        System.out.println("DataSource class: " + dataSource.getClass().getName());
    }

}
