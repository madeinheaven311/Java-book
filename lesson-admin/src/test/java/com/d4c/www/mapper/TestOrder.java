package com.d4c.www.mapper;

import com.d4c.www.entity.Order;
import com.d4c.www.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Test;

import java.util.Date;

public class TestOrder {
    private final SqlSession sqlSession = MyBatisUtil.getFactory("mybatis.xml").openSession();
    private final OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

    @Test
    public void insert() {
        Order order = new Order();
        order.setOrderNumber("订单号");
        order.setTotalFee(1.1D);
        order.setOrderInfo("描述");
        order.setOrderDeleted(0);
        order.setCreateTime(new Date());
        order.setModifyTime(new Date());
        System.out.println(orderMapper.insert(order));
        System.out.println(order);
    }

    @Test
    public void select() {
        Order condition = new Order();
        condition.setOrderId(9);
        condition.setOrderDeleted(0);
        System.out.println(orderMapper.select(condition));
    }

    @Test
    public void update() {
        Order order = new Order();
        order.setOrderNumber("订单号-1");
        order.setOrderInfo("描述-1");
        Order condition = new Order();
        condition.setOrderId(1);
        condition.setOrderDeleted(0);
        System.out.println(orderMapper.update(order, condition));
    }

    @Test
    public void delete() {
        Order condition = new Order();
        condition.setOrderId(1);
        System.out.println(orderMapper.delete(condition));
    }

    @Test
    public void deleteByOrderIds() {
        Integer[] orderIds = new Integer[]{998, 999};
        System.out.println(orderMapper.deleteByOrderIds(orderIds));
    }

    @After
    public void after() {
        sqlSession.commit();
        sqlSession.close();

    }

}
