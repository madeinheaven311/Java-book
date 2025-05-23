package com.d4c.www.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable {

    private Long id;
    private String sn;
    private Double totalAmount;
    private Double payAmount;
    private Long payType;
    private String info;
    private Long state;
    private Long fkMemberId;
    private Long fkCouponsId;
    private String username;
    private Date created;
    private Date updated;
    
}
