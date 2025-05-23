package com.d4c.www.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public abstract class BaseEntity implements Serializable {

    private Date createTime;
    private Date modifyTime;

}
