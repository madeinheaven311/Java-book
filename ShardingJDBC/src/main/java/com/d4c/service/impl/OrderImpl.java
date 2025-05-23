package com.d4c.service.impl;

import com.d4c.domain.Order;
import com.d4c.mapper.OrderMapper;
import com.d4c.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OrderImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public int insertOrder(BigDecimal price, Long userId, String status) {
        Order order = new Order();
        order.setPrice(price);
        order.setUser_id(userId);
        order.setStatus(status);

        return orderMapper.insert(order);
    }

    @Override
    public List<Map> selectOrderbyIds(List<Long> orderIds) {
        return null;
    }

    @Override
    public List<Map> selectAllOrder(int start, int end) {
        return null;
    }

    @Override
    public List<Map> selectOrderByUserId(Long userId) {
        return null;
    }

    @Override
    public Map selectOrderById(Long order_id) {
        return null;
    }
}
