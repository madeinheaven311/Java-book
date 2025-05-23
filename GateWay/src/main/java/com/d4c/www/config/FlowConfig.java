package com.d4c.www.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.*;

@Configuration
public class FlowConfig {

    /** 视图解析器列表: 用于展示限流之后的响应内容 */
    private final List<ViewResolver> viewResolvers;
    /** 读写器: 用于操作请求和响应 */
    private final ServerCodecConfigurer serverCodecConfigurer;

    /**
     * 有参构造器
     * @param provider 对象提供者实例
     * @param serverCodecConfigurer 请求读写器
     */
    public FlowConfig(ObjectProvider<List<ViewResolver>> provider,
                          ServerCodecConfigurer serverCodecConfigurer) {
        // 在构造器中，获取视图解析器列表并上升作用域
        this.viewResolvers = provider.getIfAvailable(Collections::emptyList);
        // 将请求读写器直接上升作用域
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    //全局过滤器注入
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    //异常处理器注入
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    //提前设置异常处理器的处理规则
    @PostConstruct
    public void initExceptionResponse() {
        GatewayCallbackManager.setBlockHandler((exchange, throwable) -> {

            // 存储响应状态码和响应数据
            Map<String, Object> map = new HashMap<>(2);
            map.put("code", 0);
            map.put("message", "QPS过高，API网关执行限流操作");

            // 响应
            return ServerResponse
                    // 设置200响应状态
                    .status(HttpStatus.OK)
                    // 设置响应MIME类型为JSON格式
                    .contentType(MediaType.APPLICATION_JSON)
                    // 将Map数据转为JSON并设置到响应体中
                    .body(BodyInserters.fromValue(map));
        });
    }


    //提前为全局过滤器设置要过滤的分组
    @PostConstruct
    public void initApiDefinition() {

        // 设置两个分组，分别起名 `groupA` 和 `groupB`
        ApiDefinition groupA = new ApiDefinition("groupA");
        ApiDefinition groupB = new ApiDefinition("groupB");

        // 凡是 `/order-app/api/v1/**` 开头的请求，都默认归于A组
        ApiPathPredicateItem predicateItemA = new ApiPathPredicateItem();
        predicateItemA.setPattern("/order-app/api/v1/**");
        predicateItemA.setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX);
        groupA.setPredicateItems(new HashSet<>() {{
            add(predicateItemA);
        }});

        // 将完全匹配 `/order-app/api/v0/test` 的请求视为B组
        ApiPathPredicateItem predicateItemB = new ApiPathPredicateItem();
        predicateItemB.setPattern("/order-app/api/v0/gateway-test");
        groupB.setPredicateItems(new HashSet<>() {{
            add(predicateItemB);
        }});

        // 将自定义分组信息加载到API网关的API分组中
        Set<ApiDefinition> definitions = new HashSet<>();
        definitions.add(groupA);
        definitions.add(groupB);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);


    }

    //提前将分组加入规则，并设置限流规则
    @PostConstruct
    public void initGatewayRules() {

        // 设置A组的限流规则: QPS阈值为1，限流3秒钟
        GatewayFlowRule ruleA = new GatewayFlowRule("groupA");
        ruleA.setCount(1).setIntervalSec(3);

        // 设置A组的限流规则: QPS阈值为5，限流10秒钟
        GatewayFlowRule ruleB = new GatewayFlowRule("groupB");
        ruleB.setCount(5).setIntervalSec(10);

        // 将限流规则的set集合加载到API网关的规则管理器中
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(ruleA);
        rules.add(ruleB);
        GatewayRuleManager.loadRules(rules);
    }
}