package com.d4c.www.filter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ReqGatewayFilterFactory
        extends AbstractGatewayFilterFactory<ReqGatewayFilterFactory.Config> {


    /** 用于接收和存储Reg的两个值: requestHeader和requestParam */
    @Data
    static class Config {
        /** 接收主配中配置的请求头查看开关 */
        private Boolean requestHeader;
        /** 接收主配中配置的请求参数查看开关 */
        private Boolean requestParam;
    }


    @Override
    public List<String> shortcutFieldOrder() {
        // 将主配中的Req属性的第1个值，对位赋值给内部类的requestHeader属性
        // 将主配中的Req属性的第2个值，对位赋值给内部类的requestParam属性
        return Arrays.asList("requestHeader", "requestParam");
    }


    /** 将内部配置类传递给父类进行处理 */
    public ReqGatewayFilterFactory() {
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {

        // 函数式接口 `GatewayFilter` 可以直接使用lambda表达式返回
        return (exchange, chain) -> {

            // 获取当前的请求
            ServerHttpRequest request = exchange.getRequest();

            // 获取指定请求头中的值
            if (config.getRequestHeader()) {
                System.out.println("请求头中a的值为" + request.getHeaders().get("a"));
            }

            // 获取指定请求参数中的值
            if (config.getRequestParam()) {
                System.out.println("请求参数中b的值为" + request.getQueryParams().get("b"));
            }

            // 放行请求到下一个过滤器
            return chain.filter(exchange);
        };
    }

}