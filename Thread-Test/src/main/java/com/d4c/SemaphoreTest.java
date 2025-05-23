package com.d4c;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/** @author JoeZhou */
public class SemaphoreTest {

    @Test
    @SneakyThrows
    public void testSemaphore() {

        // 创建信号量实例，初始信号量为3
        Semaphore semaphore = new Semaphore(3);

        // 循环创建10个子线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 获取一个信号，获取不成功当前线程会阻塞等待
                    semaphore.acquire();
                    // 模拟线程执行任务
                    System.out.println(Thread.currentThread().getName() + " 执行线程任务...");
                    TimeUnit.SECONDS.sleep(3L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放一个信号，此时其他线程可以重新获取这个信号
                    semaphore.release();
                }
            }, "子线程" + i).start();
        }

        // 阻塞junit线程
        TimeUnit.SECONDS.sleep(30L);
    }
}