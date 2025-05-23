package com.d4c.www.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class Duck implements Serializable {

    String name;
    Integer age;

}
