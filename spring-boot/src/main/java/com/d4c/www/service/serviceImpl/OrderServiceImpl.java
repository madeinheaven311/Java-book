package com.d4c.www.service.serviceImpl;

import com.d4c.www.entity.Order;
import com.d4c.www.mapper.OrderMapper;
import com.d4c.www.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void insert(Order order) {

        orderMapper.insert(order);

    }

    @Override
    public Order selectById( Long id) {

        Order condition = new Order();
        condition.setId(id);
        return orderMapper.select(condition).get(0);

    }

    @Override
    public List<Order> selectAll() {

        return orderMapper.select( new Order() );

    }

    @Override
    public void updateById(Order order, Long id) {

        Order condition = new Order();
        condition.setId(id);
        orderMapper.update( order, condition );

    }

    @Override
    public void deleteById(Long id) {

        Order condition = new Order();
        condition.setId(id);
        orderMapper.delete( condition );

    }

    @Override
    public PageInfo<Order> page(Integer pageNum, Integer pageSize) {

        PageHelper.startPage( pageNum, pageSize);
        List<Order> courses = orderMapper.select(new Order());
        return new PageInfo<>(courses);

    }
}
