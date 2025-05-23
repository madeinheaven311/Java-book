package com.d4c.www.contoller;

import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rabbitmq/topic")
public class TopicController {

    /** RabbitMQ推荐使用 `o.s.a.r.c.RabbitTemplate` 作为Java客户端 */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @SneakyThrows
    @GetMapping("/send/{message}/{routingKey}")
    public String send(@PathVariable String message, @PathVariable String routingKey) {
		String exchangeName = "joezhou.topic.exchange";

		// 发送消息: 交换机名，路由键，消息
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        return "消息发送成功";
    }
}