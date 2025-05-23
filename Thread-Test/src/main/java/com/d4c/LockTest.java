package com.d4c;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** @author JoeZhou */
public class LockTest {

    private static class LockDemo implements Runnable {

        /** 创建一个可重入锁实例 */
        private final Lock lock = new ReentrantLock();

        @Override
        public void run() {
            // 添加可重入锁
            lock.lock();
            try {

                // 每隔1秒打印一次当前线程名称，共打印5次
                for (int i = 0; i < 5; i++) {
                    TimeUnit.MILLISECONDS.sleep(200L);
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }

                // 调用method()方法，测试ReentrantLock是否可重入
                method();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                // 释放可重入锁，必须写在finally块中
                lock.unlock();
            }
        }

        /**
         * 方法内使用lock进行上锁和解锁
         */
        private void method() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ": 执行完毕");
            } finally {
                // 释放锁的代码必须写在finally块中
                lock.unlock();
            }
        }
    }

    @Test
    @SneakyThrows
    public void testLock() {
        Runnable runnable = new LockDemo();
        Thread t01 = new Thread(runnable, "子线程A");
        Thread t02 = new Thread(runnable, "子线程B");

        // 启动两个线程并插队执行
        t01.start();
        t02.start();
        t01.join();
        t02.join();
    }
}