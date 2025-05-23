package com.d4c.www.component;

import cn.hutool.core.util.ObjectUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author JoeZhou
 */
@Slf4j
@Component
public class MyAsyncTask {

    @SneakyThrows
    @Async
    public Future<String> taskA() {

        // 睡眠3秒钟，模拟任务耗时
        TimeUnit.SECONDS.sleep(5);


        // 构建返回值并返回，无返回值时参数传入null即可
        return new AsyncResult<>("异步任务A执行完毕");
    }

    @SneakyThrows
    @Async
    public Future<String> taskB() {

        // 睡眠3秒钟，模拟任务耗时
        TimeUnit.SECONDS.sleep(10);

        // 构建返回值并返回，无返回值时参数传入null即可
        return new AsyncResult<>("异步任务B执行完毕");
    }
}
