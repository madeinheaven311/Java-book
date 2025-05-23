package com.d4c.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderService {
 

    int insertOrder(BigDecimal price, Long userId,String status);


    List<Map> selectOrderbyIds(List<Long> orderIds);


    List<Map> selectAllOrder(int start,int end);

    List<Map> selectOrderByUserId(Long userId);


    Map selectOrderById( Long order_id);

}