package com.d4c;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.*;

/** @author JoeZhou */
public class ThreadPoolExecutorTest {

    @SneakyThrows
    @Test
    public void testThreadPoolExecutor() {

        // 创建一个线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                // 核心线程数，线程池维护的最少线程数量，即使空闲也不会给归还给OS
                2,
                // 线程池维护的最多线程数量，即当核心线程不够了，最大能拓展到多少
                4,
                // 非核心线程所允许的最大的空闲时间，某个线程的空闲时间如果超过此指定值，会被归还给OS
                3, TimeUnit.SECONDS,
                // 线程池所使用的工作队列类型，即BlockingQueue类型
                new ArrayBlockingQueue<>(3),
                // 线程工厂，用于产生线程，默认使用DefaultThreadFactory
                Executors.defaultThreadFactory(),
                // 线程池对拒绝任务采取的拒绝策略，默认使用AbortPolicy
                new ThreadPoolExecutor.AbortPolicy()
        );

        // Runnable线程任务使用 execute() 提交到线程池中
        threadPool.execute(() -> System.out.println("runnable.."));

        TimeUnit.SECONDS.sleep(2L);

        // Callable线程任务使用 submit() 提交到线程池中
        Future<Integer> future = threadPool.submit(() -> {
            System.out.println("callable..");
            return 9527;
        });

        // 获取Callable任务实例的返回值
        System.out.println(future.get());

        // 关闭线程池
        threadPool.shutdown();
    }
}