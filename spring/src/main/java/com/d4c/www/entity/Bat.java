package com.d4c.www.entity;

import lombok.Data;

@Data
public class Bat {

    public Bat() {
        System.out.println("Bat的无参构造器被调用..");
    }

    public void init() {
        System.out.println("Bat实例的init()被调用..");
    }

    public void destroy() {
        System.out.println("Bat实例的destroy()被调用..");
    }
}
