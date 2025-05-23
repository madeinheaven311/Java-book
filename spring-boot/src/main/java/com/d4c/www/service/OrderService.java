package com.d4c.www.service;

import com.d4c.www.entity.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {

    void insert(Order order );

    Order selectById(Long id );

    List<Order> selectAll();

    void updateById(Order order, Long id );

    void deleteById( Long id );

    PageInfo<Order> page( Integer pageNum, Integer pageSize );

}
