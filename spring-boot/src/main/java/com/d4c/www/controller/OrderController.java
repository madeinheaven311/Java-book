package com.d4c.www.controller;


import cn.hutool.core.bean.BeanUtil;
import com.d4c.www.dto.OrderDTO;
import com.d4c.www.entity.Order;
import com.d4c.www.entity.result.Result;
import com.d4c.www.entity.result.ResultCode;
import com.d4c.www.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Tag(name = "订单控制器", description = "对订单信息进行操作")
@RestController
@RequestMapping("api/v0/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/insert")
    public Object insert(@RequestBody OrderDTO dto){

        Order order= BeanUtil.copyProperties( dto, Order.class );
        orderService.insert( order );
        return new Result(ResultCode.SUCCESS);

    }

    @GetMapping("/deleteById")
    public Object deleteById(Long id){

        orderService.deleteById( id );
        return new Result(ResultCode.SUCCESS);

    }

    @PostMapping("/updateById")
    public Object updateById(@RequestBody OrderDTO dto, Long id){

        Order order= BeanUtil.copyProperties( dto, Order.class );
        orderService.updateById( order , id );
        return new Result(ResultCode.SUCCESS);

    }


    @GetMapping("/selectByiD")
    public Object selectById(Long id){

        return orderService.selectById(id);

    }

    @GetMapping("/selectAll")
    public Object selectAll(){

        return orderService.selectAll();

    }


    @GetMapping("/page")
    public Object page( Integer pageNum, Integer pageSize ){

        return orderService.page(pageNum, pageSize);

    }

}
