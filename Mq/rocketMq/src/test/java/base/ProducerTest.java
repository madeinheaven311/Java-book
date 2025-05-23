package base;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author JoeZhou
 */
public class ProducerTest {

    @Test
    @SneakyThrows
    public void testProducer() {

        // 创建一个生产者实例并纳入 `test-producer-group` 生产者组，组名自动创建
        DefaultMQProducer producer = new DefaultMQProducer("test-producer-group");

        // 指定NameSrv地址，集群逗号分隔
        producer.setNamesrvAddr("192.168.216.4:9876");

        // 启动生产者
        producer.start();

        // 创建10个消息实例并发送到Broker，主题和标签都可以自动创建
        for (int i = 0; i < 10; i++) {

            String topic = "test-topic";
            String tag = "test-tag";
            byte[] content = ("你好" + i).getBytes(StandardCharsets.UTF_8);

            // 创建消息实例，指定 `test-topic` 主题和 `test-tag` 标签
            Message message = new Message(topic, tag, content);

            // 将消息同步发送到Broker中，10秒超时，返回SendResult实例
            SendResult sendResult = producer.send(message, 10000);

            // 查看发送消息的结果
            System.out.printf("%d号消息[ID=%s]的发送状态为: %s\n",
                    i,
                    sendResult.getMsgId(),
                    sendResult.getSendStatus());
        }
    }
}