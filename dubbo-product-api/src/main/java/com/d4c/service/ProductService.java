package com.d4c.service;

/** @author JoeZhou */
public interface ProductService {

    /**
     * 按商品主键查询商品信息
     *
     * @param id 商品ID
     * @return 商品信息
     */
    String getById(Long id);
}