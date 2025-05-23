package com.d4c.www.component;

import cn.hutool.core.util.ObjectUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ServiceAspect {

    @SneakyThrows
    @Around("execution(public * com.d4c.www.service.impl.*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {

        // 获取方法参数
        Object[] args = pjp.getArgs();

        // 获取类名
        String className = pjp.getTarget().toString();

        // 获取方法名
        String methodName = pjp.getSignature().getName();

        // 前置通知: 检查参数中是否存在空值
        if (ObjectUtil.hasNull(args)) {
            throw new RuntimeException("前置通知: 业务方法 " + className + "." + methodName + " 中存在null值参数");
        }

        // 调用目标方法
        Object returnValue = pjp.proceed(args);

        // 后置通知: 记录业务层调用日志
        log.info("成功调用 {} 类中的 {} 方法", className, methodName);

        // 返回目标方法的返回值
        return returnValue;
    }
}
