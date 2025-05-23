package com.d4c.www.mapper;

import com.d4c.www.entity.Order;

import java.util.List;

public interface OrderMapper {

    /**
     * 添加Order记录
     *
     * @param order 添加字段
     * @return 影响条目数
     */
    int insert(Order order);

    /**
     * 按条件查询Order记录
     *
     * @param condition 查询条件
     * @return 多条Order记录
     */
    List<Order> select(Order condition);

    /**
     * 按条件修改Order记录
     *
     * @param order     修改字段
     * @param condition 修改条件
     * @return 影响条目数
     */
    int update(Order order, Order condition);

    /**
     * 按条件删除Order记录
     *
     * @param condition 删除条件
     * @return 影响条目数
     */
    int delete(Order condition);

    /**
     * 按Order主键数组批量删除多条Order记录
     *
     * @param orderIds Order主键数组
     * @return 影响条目录
     */
    int deleteByOrderIds(Integer[] orderIds);
}
