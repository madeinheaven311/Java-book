package com.d4c;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** @author JoeZhou */
public class ReentrantReadWriteLockTest {

    /** 本类用于测试ReentrantReadWriteLock的读写效果 */
    private static class ReadWriteLockDemo {

        /** 读写锁实例 */
        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        /** 读锁 */
        private final Lock readLock = lock.readLock();

        /** 写锁 */
        private final Lock writeLock = lock.writeLock();

        /** 模拟读操作耗时5秒 */
        void read() {
            // 获取读锁
            readLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "正在读...");
                TimeUnit.SECONDS.sleep(5L);
                System.out.println(Thread.currentThread().getName() + "读完了...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放读锁
                readLock.unlock();
            }
        }

        /** 模拟写操作耗时2秒 */
        void write() {
            // 获取写锁
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "正在写...");
                TimeUnit.SECONDS.sleep(2L);
                System.out.println(Thread.currentThread().getName() + "写完了...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放写锁
                writeLock.unlock();
            }
        }
    }

    @SneakyThrows
    @Test
    public void testReadWriteLock() {

        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();

        // 创建4个线程
        // 相当于 new Thread(() -> readWriteLockDemo.read(), "读线程A")
        Thread t01 = new Thread(readWriteLockDemo::read, "读线程A");
        Thread t02 = new Thread(readWriteLockDemo::read, "读线程B");
        Thread t03 = new Thread(readWriteLockDemo::write, "写线程C");
        Thread t04 = new Thread(readWriteLockDemo::write, "写线程D");

        // 启动4个线程并插队执行
        t01.start(); t02.start(); t03.start(); t04.start();
        t01.join(); t02.join(); t03.join(); t04.join();

    }
}