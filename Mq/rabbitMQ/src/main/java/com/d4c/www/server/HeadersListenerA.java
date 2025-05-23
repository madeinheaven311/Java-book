package com.d4c.www.server;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeadersListenerA {
    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("joezhou.headers.queue-a"))
    public void consume(Message message) {
        System.out.println("HeadersListenerA收到消息: " + message.getBody());
    }
}