package com.d4c.www.server;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue("joezhou.topic.queue-b"))
public class TopicListenerB {
    @RabbitHandler
    public void consume(String message) {
        System.out.println("TopicListenerB收到消息: " + message);
    }
}
