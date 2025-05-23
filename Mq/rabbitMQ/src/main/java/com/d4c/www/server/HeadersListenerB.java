package com.d4c.www.server;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeadersListenerB {

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("joezhou.headers.queue-b"))
    public void consume(Message message) {
        System.out.println("HeadersListenerB收到消息: " + message.getBody());
    }
}