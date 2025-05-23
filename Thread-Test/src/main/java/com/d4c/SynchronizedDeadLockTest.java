package com.d4c;

import lombok.SneakyThrows;
import org.junit.Test;

/** @author JoeZhou */
public class SynchronizedDeadLockTest {

    /** 测试synchronized死锁现象 */
    private static class DeadLock implements Runnable {
    
        private final Object objA = new Object();
        private final Object objB = new Object();

        @SneakyThrows
        @Override
        public void run() {
            if (Thread.currentThread().getName().equals("子线程A")) {
                synchronized (objA) {
                    System.out.println("if: objA");
                    synchronized (objB) {
                        System.out.println("if: objB");
                    }
                }
            } else {
                synchronized (objB) {
                    System.out.println("else: objB");
                    synchronized (objA) {
                        System.out.println("else: objA");
                    }
                }
            }
        }
    }

    @SneakyThrows
    @Test
    public void testDeadLock() {
    
        // 创建线程类
        Runnable runnable = new DeadLock();

        // 创建两个线程
        Thread t01 = new Thread(runnable, "子线程A");
        Thread t02 = new Thread(runnable, "子线程B");

		// 启动两个线程并插队运行
        t01.start();
        t02.start();
        t01.join();
        t02.join();

        System.out.println("主线程结束");
    }
}