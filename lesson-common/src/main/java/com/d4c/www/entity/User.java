package com.d4c.www.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer userId;
    private String username;
    private String password;
    private String realName;
    private String nickName;
    private Integer gender;
    private Integer age;
    private String idCard;
    private String avatarUrl;
    private String phone;
    private String userInfo;
    private Integer userDeleted;
    private Date createTime;
    private Date modifyTime;
}
