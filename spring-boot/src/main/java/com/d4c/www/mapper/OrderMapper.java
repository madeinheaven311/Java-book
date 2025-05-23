package com.d4c.www.mapper;


import com.d4c.www.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    int insert(Order order);
    int delete(Order condition);
    int update(Order order, Order condition);
    List<Order> select(Order condition);

}
