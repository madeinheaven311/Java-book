package com.d4c.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AliPayFaceToFaceModel implements Serializable {
    private String outTradeNo;
    private String subject;
    private String totalAmount;
    private String body;
}