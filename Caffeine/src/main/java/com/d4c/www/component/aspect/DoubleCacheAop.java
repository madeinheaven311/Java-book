package com.d4c.www.component.aspect;

import com.alibaba.fastjson.JSON;
import com.d4c.www.component.annotation.DoubleCache;
import com.d4c.www.component.myEnum.CacheType;
import com.github.benmanes.caffeine.cache.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

@Configuration
@Aspect
public class DoubleCacheAop {

    @Resource
    Cache<String,Object> caffeineCache;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(com.d4c.www.component.annotation.DoubleCache)")
    public void doubleCachePointcut(){}


    @Around("doubleCachePointcut()")
    public Object cacheAround(ProceedingJoinPoint point) throws Throwable {
        //获取切点方法签名,签名是对切点的具体描述，可用于获取切点的的1类型和具体信息
        Signature signature = point.getSignature();
        if (signature instanceof MethodSignature){
            MethodSignature methodSignature = (MethodSignature) signature;
            //获取切点方法
            Method method = methodSignature.getMethod();
            //获取切点注解
            DoubleCache annotation = method.getAnnotation(DoubleCache.class);
            //注解属性
            String key = annotation.key();
            CacheType type = annotation.type();

            //获取参数名
            String[] parameterNames = methodSignature.getParameterNames();
            //获取参数值
            Object[] args = point.getArgs();

            TreeMap<String, Object> treeMap = new TreeMap<>();
            for (int i = 0; i < parameterNames.length; i++) {
                treeMap.put(parameterNames[i],args[i]);
            }
            //入参解析
            String parse = parse(key, treeMap);
            String realKey = annotation.value() +":"+ parse;

            if (type.equals(CacheType.DELETE)){
                caffeineCache.invalidate(realKey);
                redisTemplate.delete(realKey);
            }else if (type.equals(CacheType.PUT)){
                //执行代理方法
                Object proceed = point.proceed();
                caffeineCache.put(realKey,proceed);
                redisTemplate.opsForValue().set(realKey,proceed,1000, TimeUnit.MILLISECONDS);
                return proceed;
            }else {
                Object ifPresent = caffeineCache.getIfPresent(realKey);
                if (ifPresent == null){
                    Object o = redisTemplate.opsForValue().get(realKey);
                    if (o == null){
                        //执行代理方法
                        Object proceed = point.proceed();
                        caffeineCache.put(realKey,proceed);
                        redisTemplate.opsForValue().set(realKey,proceed,1000, TimeUnit.MILLISECONDS);
                        return proceed;
                    }
                    caffeineCache.put(realKey,o);
                    return JSON.parseObject(o.toString(),method.getReturnType());
                }
                return ifPresent;
            }
        }
        //执行代理方法
        return point.proceed();
    }

    public static String parse(String elString, TreeMap<String,Object> map){
        elString=String.format("#{%s}",elString);
        //创建表达式解析器
        ExpressionParser parser = new SpelExpressionParser();
        //通过evaluationContext.setVariable可以在上下文中设定变量。
        EvaluationContext context = new StandardEvaluationContext();
        map.entrySet().forEach(entry->
                context.setVariable(entry.getKey(),entry.getValue())
        );

        //解析表达式
        Expression expression = parser.parseExpression(elString, new TemplateParserContext());
        //使用Expression.getValue()获取表达式的值，这里传入了Evaluation上下文
        String value = expression.getValue(context, String.class);
        return value;
    }
}