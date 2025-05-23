package com.d4c.www.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/rabbitmq/headers")
public class HeadersController {

    /** RabbitMQ推荐使用 `o.s.a.r.c.RabbitTemplate` 作为Java客户端 */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /** routingKey 参数必须是一个JSON格式的字符串 */
    @SneakyThrows
    @GetMapping("/send/{message}/{routingKey}")
    public String send(@PathVariable String message,
                       @PathVariable String routingKey) {

        // 将JSON格式的routingKey字符串转为Map类型
        Map<String, Object> routingKeyMap = new ObjectMapper().readValue(routingKey, Map.class);

        // 创建消息属性实例
        MessageProperties messageProperties = new MessageProperties();

        // 设置消息属性实持久化和字符编码
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        messageProperties.setContentType("UTF-8");

        // 将Map类型的routingKey存放到请求头
        messageProperties.getHeaders().putAll(routingKeyMap);

        // 准备消息实例
        Message messageResult = new Message(message.getBytes(StandardCharsets.UTF_8), messageProperties);

        // 发送消息，路由键先空着
        rabbitTemplate.convertAndSend("joezhou.headers.exchange", "", messageResult);

        return "消息发送成功";
    }
}