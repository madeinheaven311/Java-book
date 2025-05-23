package com.d4c.www.entity;


import lombok.Data;

import java.util.List;

@Data
public class Clazz extends BaseEntity{
    private Integer clazzId;
    private String clazzName;

    List<Student> students;
}
