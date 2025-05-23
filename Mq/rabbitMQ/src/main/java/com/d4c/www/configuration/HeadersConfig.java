package com.d4c.www.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

/** @author JoeZhou */
@Configuration
public class HeadersConfig implements BeanPostProcessor {

    /** RabbitAdmin用来创建队列和交换机 */
    @Autowired
    private RabbitAdmin rabbitAdmin;

    /** 管理RabbitAdmin实例 */
    @Bean
    @ConditionalOnMissingBean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        // 创建RabbitAdmin实例，并设置在项目启动时将该实例自动交给Spring管理
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    /** 管理 [headers请求头模式] 下的 [队列A] */
    @Bean
    public Queue headersQueueA() {
        // args: 队列名，持久化，非私有独占队列，无消费者时不删除队列
        return new Queue("joezhou.headers.queue-a", true, false, false);
    }

    /** 管理 [headers请求头模式] 下的 [队列B] */
    @Bean
    public Queue headersQueueB() {
        // args: 队列名，持久化，非私有独占队列，无消费者时不删除队列
        return new Queue("joezhou.headers.queue-b", true, false, false);
    }

    /** 管理 [headers请求头模式] 下的 [交换机] */
    @Bean
    public HeadersExchange headersExchange() {
        // args: 交换机名，持久化，无消费者时不删除交换机
        return new HeadersExchange("joezhou.headers.exchange", true, false);
    }

    /** 将 [队列A] 绑定到指定 [交换机] */
    @Bean
    public Binding bindHeadersA() {

        // 设置一个路由键集合
        Map<String, Object> map = new HashMap<>(2);
        map.put("a", "1");
        map.put("b", "2");

        /* 请求头模式 `whereAll()` 表示全部路由键都匹配成功时才视为成功
         * 比如将路由键设置为如上的map集合，那么:
         *   当 `?routingKey = {"a": 1}` 时，消息发送失败。
         *   当 `?routingKey = {"a": 1, "b"," 2}` 时，消息发送成功。
         */
        return BindingBuilder
                .bind(headersQueueA())
                .to(headersExchange())
                .whereAll(map).match();
    }

    /** 将 [队列B] 绑定到指定 [交换机] */
    @Bean
    public Binding bindHeadersB() {

        // 设置一个路由键集合
        Map<String, Object> map = new HashMap<>(2);
        map.put("c", "3");
        map.put("d", "4");

        /* 请求头模式 `whereAny()` 表示部分路由键匹配成功时就视为成功
         * 比如将路由键设置为如上的map集合，那么:
         *   当 `?routingKey = {"c": 3}` 时，消息发送成功。
         *   当 `?routingKey = {"c": 3, "d"," 4}` 时，消息发送成功。
         */
        return BindingBuilder.bind(headersQueueB())
                .to(headersExchange())
                .whereAny(map).match();
    }

    /** Bean的后置处理器: 在项目启动后执行，自动创建队列和交换机 */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        rabbitAdmin.declareQueue(headersQueueA());
        rabbitAdmin.declareQueue(headersQueueB());
        rabbitAdmin.declareExchange(headersExchange());
        return null;
    }
}