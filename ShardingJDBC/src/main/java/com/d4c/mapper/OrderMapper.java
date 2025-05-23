package com.d4c.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.d4c.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Repository
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 插入订单
     */
    @Insert("insert into t_order(price,user_id,status)values(#{price},#{userId},#{status})")
    int insertOrder(BigDecimal price, Long userId, String status);


    /**
     * 根据id列表查询订单
     */
//    @Select("<script>" +
//            "select" +
//            " * " +
//            " from t_order t " +
//            " where t.order_id in " +
//            " <foreach collection='orderIds' open='(' separator=',' close=')' item='id'>" +
//            " #{id} " +
//            " </foreach>" +
//            "</script>")
    List<Map> selectOrderbyIds(@Param("orderIds") List<Long> orderIds);


    /**
     * 范围查询订单
     * @param start
     * @param end
     * @return
     */
    @Select("select * from t_order where user_id " + "between #{start} and #{end}")
    List<Map> selectAllOrder(@Param(value = "start") int start,@Param(value = "end") int end);

    /**
     * 根据user_id查询订单
     */
    @Select("select * from t_order where user_id = #{user_id}")
    List<Map> selectOrderByUserId(@Param(value = "user_id") Long userId);


    /**
     * 根据订单id查询订单
     */
    @Select("select * from t_order where order_id = #{order_id}")
    Map selectOrderById(@Param(value = "order_id") Long order_id);
}
