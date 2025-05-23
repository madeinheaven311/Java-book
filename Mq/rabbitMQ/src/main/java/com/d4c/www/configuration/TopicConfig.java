package com.d4c.www.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author JoeZhou */
@Configuration
public class TopicConfig implements BeanPostProcessor {

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

    /** 管理 [topic主题模式] 下的 [队列A] */
    @Bean
    public Queue topicQueueA() {
        // args: 队列名，持久化，非私有独占队列，无消费者时不删除队列
        return new Queue("joezhou.topic.queue-a", true, false, false);
    }

    /** 管理 [topic主题模式] 下的 [队列B] */
    @Bean
    public Queue topicQueueB() {
        // args: 队列名，持久化，非私有独占队列，无消费者时不删除队列
        return new Queue("joezhou.topic.queue-b", true, false, false);
    }

    /** 管理 [topic主题模式] 下的 [交换机] */
    @Bean
    public TopicExchange topicExchange() {
        // args: 交换机名，持久化，无消费者时不删除交换机
        return new TopicExchange("joezhou.topic.exchange", true, false);
    }

    /** 将 [队列A] 绑定到指定 [交换机] 并指定 [带通配符的路由键] */
    @Bean
    public Binding bindTopicA() {
        /* 主题模式通配符 `*` 用来匹配1个单词，不能单独使用
         * 比如将路由键设置为 `joezhou.*`，那么:
         *   当 `?routingKey = joezhou.a` 时，消息发送成功。
         *   当 `?routingKey = joezhou.hello` 时，消息发送成功。
         *   当 `?routingKey = joezhou` 时，消息发送失败。
         *   当 `?routingKey = joezhou.a.b` 时，消息发送失败。
         */
        return BindingBuilder
                .bind(topicQueueA())
                .to(topicExchange())
                .with("joezhou.*");
    }

    /** 将 [队列B] 绑定到指定 [交换机] 并指定 [带通配符的路由键] */
    @Bean
    public Binding bindTopicB() {
        /*
         * 主题模式通配符 `#` 用来匹配1个单词，不能单独使用
         * 比如将路由键设置为 `joezhou.#`，那么:
         *   当 `?routingKey = joezhou.a` 时，消息发送成功。
         *   当 `?routingKey = joezhou.hello` 时，消息发送成功。
         *   当 `?routingKey = joezhou` 时，消息发送成功。
         *   当 `?routingKey = joezhou.a.b` 时，消息发送成功。
         * */
        return BindingBuilder
                .bind(topicQueueB())
                .to(topicExchange())
                .with("joezhou.#");
    }

    /** Bean的后置处理器: 在项目启动后执行，自动创建队列和交换机 */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        rabbitAdmin.declareQueue(topicQueueA());
        rabbitAdmin.declareQueue(topicQueueB());
        rabbitAdmin.declareExchange(topicExchange());
        return null;
    }
}