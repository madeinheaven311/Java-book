package com.d4c.www.component.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class CacheLoggingAspect  {

    @Before("@annotation(org.springframework.cache.annotation.Cacheable)")
    public void logBeforeCacheableMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("About to execute @Cacheable method: " + methodName + " with arguments: " + Arrays.toString(args));
    }

    @AfterReturning("@annotation(org.springframework.cache.annotation.Cacheable)")
    public void logAfterCacheableMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Executed @Cacheable method: " + methodName);
    }
}
