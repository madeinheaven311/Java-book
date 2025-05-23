package com.d4c.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInsertDTO implements Serializable {
    private String username;
    private String password;
    private String realname;
    private Integer age;
    private Integer gender;
}