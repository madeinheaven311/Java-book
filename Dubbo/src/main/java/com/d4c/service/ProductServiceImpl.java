package com.d4c.service;

import org.apache.dubbo.config.annotation.DubboService;

/** @author JoeZhou */

@DubboService(version = "1.0.0")
public class ProductServiceImpl implements ProductService {

    @Override
    public String getById(Long id) {
        return id == 1L ? "小米手机" : "苹果手机";
    }
}