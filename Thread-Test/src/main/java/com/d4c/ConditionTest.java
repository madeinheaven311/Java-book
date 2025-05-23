package com.d4c;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** @author JoeZhou */
public class ConditionTest {

    /** 该类用于测试Condition的多条等待队列效果 */
    public static class ConditionDemo implements Runnable {
        /** 可重入锁 */
        private final Lock lock = new ReentrantLock();
        /** 通过可重入锁获取一条A队列 */
        private final Condition conditionA = lock.newCondition();
        /** 通过可重入锁获取一条B队列 */
        private final Condition conditionB = lock.newCondition();


        int a = 1;

        Integer b = 1;


        @Override
        public void run() {
            // await需要在同步代码中调用
            lock.lock();
            String currentThreadName = Thread.currentThread().getName();
            try {
                // 让子线程01进入conditionA等待队列，其余线程进入conditionB等待队列
                if (currentThreadName.equals("子线程01")) {
                    System.out.println(currentThreadName + "进入conditionA等待队列");
                    conditionA.await();
                } else {
                    System.out.println(currentThreadName + "进入conditionB等待队列");
                    conditionB.await();
                }
                // 当某个await状态的线程被唤醒时执行
                System.out.println(currentThreadName + "被唤醒");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        /** 唤醒ConditionA等待队列中的全部线程 */
        public void signalAllInConditionA() {
            // signalAll需要在同步代码中调用
            lock.lock();
            try {
                // 将conditionA队列中的全部线程唤醒
                conditionA.signalAll();
            } finally {
                lock.unlock();
            }
        }

        /** 唤醒ConditionB等待队列中的全部线程 */
        public void signalAllInConditionB() {
            // signalAll需要在同步代码中调用
            lock.lock();
            try {
                // 将conditionB队列中的全部线程唤醒
                conditionB.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}