package com.d4c.www.entity;


import lombok.Data;

@Data
public class Student extends BaseEntity {
    private  Integer studentId;
    private  String realName;
    private  Integer gender;
    private  String phone;
    private  Integer clazzId;

    private Clazz clazz;

}
