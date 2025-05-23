package com.d4c.www.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class Mouse {
    private String mouseName;

    public  Mouse(){

    }

    public Mouse(String mouseName) {
        this.mouseName = mouseName;
    }

    public void mouseName(){

        System.out.println("Mickey");

    }

}