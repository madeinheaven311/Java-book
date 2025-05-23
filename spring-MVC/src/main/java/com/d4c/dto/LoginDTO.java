package com.d4c.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDTO implements Serializable {
    private String username;
    private String password;

    private LoginDTO loginDTO;


}
