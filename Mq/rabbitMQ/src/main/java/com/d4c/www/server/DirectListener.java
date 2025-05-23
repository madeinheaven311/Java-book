package com.d4c.www.server;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue("joezhou.direct.queue-a"))
public class DirectListener {
    
    @RabbitHandler
    public void consume(String message) {
        System.out.println("消费到消息: " + message);
    }
}