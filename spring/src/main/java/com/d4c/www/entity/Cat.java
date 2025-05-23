package com.d4c.www.entity;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class Cat {

    public Cat() {

        System.out.println("构造器: Cat()");

    }

}
