package com.test.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/** @author JoeZhou */
@SpringBootApplication
public class EsApp {

    public static void main(String[] args) {
        SpringApplication.run(EsApp.class, args);
        List<Integer> list = new ArrayList<>();
        Object[] a = list.toArray();
    }
}