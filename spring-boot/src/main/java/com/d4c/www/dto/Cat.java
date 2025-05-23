package com.d4c.www.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class Cat implements Serializable {
    private String catName;
    private Integer catAge;
}
