package com.d4c.www.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author JoeZhou */
@Configuration
public class DirectConfig implements BeanPostProcessor {

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

    /** 管理 [direct直连模式] 下的 [队列A] */
    @Bean
    public Queue directQueueA() {
        // args: 队列名，持久化，非私有独占队列，无消费者时不删除队列
        return new Queue("joezhou.direct.queue-a", true, false, false);
    }

    /** 管理 [direct直连模式] 下的 [交换机] */
    @Bean
    public DirectExchange directExchange() {
        // args: 交换机名，持久化，无消费者时不删除交换机
        return new DirectExchange("joezhou.direct.exchange", true, false);
    }

    /** 将 [队列A] 绑定到指定 [交换机] 并设置 [路由键] */
    @Bean
    public Binding bindDirectQueueA() {
        return BindingBuilder
                .bind(directQueueA())
                .to(directExchange())
                .with("joezhou.direct.routing-key");
    }

    /** Bean的后置处理器: 在项目启动后执行，自动创建队列和交换机*/
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        rabbitAdmin.declareQueue(directQueueA());
        rabbitAdmin.declareExchange(directExchange());
        return null;
    }
}