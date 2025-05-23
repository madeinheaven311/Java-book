package com.d4c.www.contoller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("/api/v1/rabbitmq/ttl")
public class TtlController {

    /**
     * RabbitMQ推荐使用 `o.s.a.r.c.RabbitTemplate` 作为Java客户端
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send/{message}")
    public String send(@PathVariable String message) {

        long delayTimes = 5000;

        // 给延迟队列发送消息
        rabbitTemplate.convertAndSend(
                "joezhou.ttl.ttl-exchange",
                "joezhou.ttl.ttl-routing-key",
                message,
                // 给消息设置延迟毫秒值
                msg -> {
                    msg.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                    return msg;
                });
        return "消息发送成功";
    }
}