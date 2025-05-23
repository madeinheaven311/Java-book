package com.d4c.www.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class TokenGlobalFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取响应
        ServerHttpResponse response = exchange.getResponse();
        // 从请求参数中获取token字符串
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        // 判断token字符串是否为空白字符
        if (StringUtils.isBlank(token)) {
            // 记录错误日志
            System.out.println("请求中未携带token");
            // 设置响应的状态码为401: 401表示未认证
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 直接执行响应操作，相当于阻止程序运行
            return response.setComplete();
        }
        // 放行请求
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return 0;
    }
}