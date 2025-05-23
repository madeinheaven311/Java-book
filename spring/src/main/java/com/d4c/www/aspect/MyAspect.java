package com.d4c.www.aspect;

import com.d4c.www.entity.Dog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

public class MyAspect {

    /** 前置通知方法 */
    public void beforeAdvice(JoinPoint joinPoint) {
        // 获取业务方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.println("前置通知: 鉴权...");
        System.out.println("前置通知: 获取到参数: " + Arrays.toString(args));
    }


    /** 后置通知方法 */
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("后置通知: 日志...");
    }


    /** 异常通知方法: 允许直接在参数中获取异常对象 */
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        System.out.println("异常通知: " + ex.getMessage());
    }


    /** 返回通知方法: 允许直接在参数中获取返回值对象 */
    public void afterReturningAdvice(JoinPoint joinPoint, Dog dog) {
        dog.setDogName("小白");
        System.out.println("返回通知: 修改返回值的dogName属性");
    }


    /** 环绕通知方法: 返回值必须是Object，参数必须是ProceedingJoinPoint */
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        // 执行正常的业务方法
        Object returnValue = null;
        try {
            // 获取业务方法的参数
            Object[] args = pjp.getArgs();
            System.out.println("进入环绕通知..");
            System.out.println("\tAOP前置通知: 鉴权");
            System.out.println("\tAOP获取参数: " + Arrays.toString(args));
            // 代理执行: 反射并执行业务方法，其返回值就是业务方法的原返回值
            returnValue = pjp.proceed(args);
            System.out.println("\tAOP后置通知: 日志");
            System.out.println("\tAOP返回通知: 日志");
        } catch (Throwable e) {
            System.out.println("\tAOP异常通知: " + e.getMessage());
        }
        return returnValue;
    }
}
