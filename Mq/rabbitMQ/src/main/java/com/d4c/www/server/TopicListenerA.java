package com.d4c.www.server;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue("joezhou.topic.queue-a"))
public class TopicListenerA {
    @RabbitHandler
    public void consume(String message) {
        System.out.println("TopicListenerA收到消息: " + message);
    }
}