package com.d4c.www.component.annotation;

import com.d4c.www.component.myEnum.CacheType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

//作用在方法或类上
@Target({ElementType.METHOD,ElementType.TYPE})
//运行时起作用
@Retention(RetentionPolicy.RUNTIME)
//这个注解表示当一个类被标注了此注解，它的子类将自动继承这个注解
@Inherited
//当使用javadoc生成文档时，该注解会被包含在生成的文档中
@Documented
public @interface DoubleCache {
    @AliasFor("cacheName")
    String value() default "";
    @AliasFor("value")
    String cacheName() default "";
    String key() default ""; //支持springEl表达式
    long l2TimeOut() default 120;
    CacheType type() default CacheType.FULL;
}