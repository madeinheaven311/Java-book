package com.d4c;

import lombok.SneakyThrows;

import java.util.concurrent.*;

public class ThreadPool {

    @SneakyThrows
    public void testCallable() {


        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws java.lang.Exception {

                TimeUnit.SECONDS.sleep(2L);
                System.out.println("我是一个callable方法");
                return 111;
            }
        };



        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("我是一个runnable方法");
            }
        };


        ExecutorService threadPool = Executors.newCachedThreadPool();

        Future<Integer> future = threadPool.submit(callable);
        threadPool.execute(runnable);
        System.out.println("我如果立刻出现说明callable不阻塞任务提交");

        System.out.println("call返回值: " + future.get());
;
    }

}
