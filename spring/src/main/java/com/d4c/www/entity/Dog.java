package com.d4c.www.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data
public class Dog {
    private String dogName;
    private Integer dogAge;
    private List<String> hobby;
    private Cat catFriend;
    private String gender;
    /**品种*/
    private String breeds;

    public Dog() {
        System.out.println("构造器: Dog()");
    }

    public Dog(String gender, String breeds) {
        System.out.println("构造器: Dog(String gender, String breeds)");
        this.gender = gender;
        this.breeds = breeds;
    }
}
