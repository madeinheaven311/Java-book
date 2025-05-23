package com.d4c.www.componentTest;

import cn.hutool.core.util.ServiceLoaderUtil;
import com.d4c.www.SpringBootApp;
import com.d4c.www.component.MyAsyncTask;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author JoeZhou
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
public class AsyncTest {

    @Autowired
    private MyAsyncTask myAsyncTask;

    @SneakyThrows
    @Test
    public void task() {
        // fork
        Future<String> futureA = myAsyncTask.taskA();
        Future<String> futureB = myAsyncTask.taskB();
        // 轮询
        System.out.println(futureA.get());
        System.out.println(futureB.get());

        System.out.println("主线程结束");
    }
}
