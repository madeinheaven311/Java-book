package com.d4c.www;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/** @author JoeZhou */
public class ConsumerBTest {

    /** 为了防止主线程退出，使用main替代Junit方法 */
    @SneakyThrows
    public static void main(String[] args) {
    
        // 创建一个消费者实例并纳入指定名称的消费者组，组名自动创建
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test-consumer-group");
        
        // 指定NameSrv地址，集群逗号分隔
        consumer.setNamesrvAddr("192.168.216.4:9876");

        // 消费者订阅主题：指定主题，标签和内容，标签: 支持 `||` 分隔和 `*` 全部
        // org.apache.rocketmq.common.message.Message
        String topic = "test-topic";
        String tag = "test-tag";
        consumer.subscribe(topic, tag);
        
        // 消费者设置监听
        consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
            try {
                messages.forEach(messageExt ->
                        System.out.println("ConsumerB消费成功: " + new String(messageExt.getBody())));
                // 返回消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                System.out.println("ConsumerB消费失败: " + e);
                // 稍后重新尝试
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        
        // 启动消费者
        consumer.start();
        
        System.out.println("ConsumerB启动完毕，等待消费..");
    }
}