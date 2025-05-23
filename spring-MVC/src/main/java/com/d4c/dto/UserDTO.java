package com.d4c.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties("password")
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy.class)
public class UserDTO implements Serializable {

    private Integer userId;

    private String userName;

    private Integer password;

    @JsonIgnore
    private Integer idCard;

    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "Asia/Shanghai")
    private Date birthDay;
}
