package com.d4c.www.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable {
    private Integer orderId;
    private String orderNumber;
    private Double totalFee;
    private String orderInfo;
    private Integer orderDeleted;
    private Date createTime;
    private Date modifyTime;
}
