package com.d4c.www.predicates;

import com.alibaba.nacos.shaded.com.google.common.base.Predicate;
import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;

@Component
public class AgeRoutePredicateFactory
        extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

    /** 用于接收主配断言配置中的 `Age` 值 */
    @Data
    static class Config {
        /** 接收主配中配置的最小年龄 */
        private Integer minAge;
        /** 接收主配中配置的最大年龄 */
        private Integer maxAge;
    }

    /** 将内部配置类传递给父类进行处理 */
    public AgeRoutePredicateFactory() {
        super(Config.class);
    }


    @Override
    public List<String> shortcutFieldOrder() {
        // 对位赋值
        return Arrays.asList("minAge", "maxAge");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {

        // 函数式接口 `Predicate` 可以直接使用lambda表达式返回
        return serverWebExchange -> {

            // 获取首个请求参数age
            String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");

            // 若请求中没有携带age参数，则直接放行
            if (null == ageStr) {
                return true;
            }

            // 判断age是否符合要求（在主配中配置的范围），符合则放行
            int age = Integer.parseInt(ageStr);
            return age > config.getMinAge() && age < config.getMaxAge();
        };
    }
}