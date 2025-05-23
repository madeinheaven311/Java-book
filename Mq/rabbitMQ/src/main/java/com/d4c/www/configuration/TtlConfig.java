package com.d4c.www.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author JoeZhou */
@Configuration
public class TtlConfig implements BeanPostProcessor {

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

    /** 管理 [超时交换机] */
    @Bean
    DirectExchange timeoutExchange() {
        return ExchangeBuilder
		        .directExchange("joezhou.ttl.timeout-exchange")
                .durable(true)
	            .build();
    }

    /** 管理 [延迟交换机] */
    @Bean
    DirectExchange ttlExchange() {
        return ExchangeBuilder
		        .directExchange("joezhou.ttl.ttl-exchange")
                .durable(true)
                .build();
    }

    /** 管理 [超时队列]：全部超时的消息都会被转移到这个队列中 */
    @Bean
    public Queue timeoutQueue() {
        return new Queue("joezhou.ttl.timeout-queue");
    }

    /** 管理 [延迟队列]：存放所有设置了超时，但还未超时的消息 */
    @Bean
    public Queue ttlQueue() {
        return QueueBuilder
		        .durable("joezhou.ttl.ttl-queue")
                // 消息超时后转发到 dead-order-exchange 交换机
                .withArgument("x-dead-letter-exchange",
		                    "joezhou.ttl.timeout-exchange")
                // 消息超时后转发到 dead-order-exchange 交换机时指定的路由键
                .withArgument("x-dead-letter-routing-key",
	                        "joezhou.ttl.timeout-routing-key")
                .build();
    }

    /** 将 [超时队列] 绑定到 [超时交换机] 并指定 [超时路由键] */
    @Bean
    Binding timeoutBinding(DirectExchange timeoutExchange, Queue timeoutQueue) {
        return BindingBuilder
		        .bind(timeoutQueue)
		        .to(timeoutExchange)
                .with("joezhou.ttl.timeout-routing-key");
    }

    /** 将 [延迟队列] 绑定到 [延迟交换机] 并指定 [延迟路由键] */
    @Bean
    Binding ttlBinding(DirectExchange ttlExchange, Queue ttlQueue) {
        return BindingBuilder.bind(ttlQueue)
		        .to(ttlExchange)
                .with("joezhou.ttl.ttl-routing-key");
    }

    /** Bean的后置处理器: 在项目启动后执行，自动创建队列和交换机 */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        rabbitAdmin.declareQueue(timeoutQueue());
        rabbitAdmin.declareQueue(ttlQueue());
        rabbitAdmin.declareExchange(timeoutExchange());
        rabbitAdmin.declareExchange(ttlExchange());
        return null;
    }
}