package com.d4c;

import org.junit.Test;

/** @author JoeZhou */
public class SynchronizedReentryTest {

    /** 本类用于测试synchronized的重入性 */
    private static class ReentryLock {
    
        /** 递归方法 */
        private int method(int num) {
        
            // 发现是同一个线程，允许重入，无需重新获取锁，若不允许重入，则会阻塞在这里
            synchronized (this) {
                System.out.println("num: " + num);
                if (num <= 1) {
                    // 递归出口
                    return 1;
                }
                // 在线程释放锁之前递归调用method方法
                return method(--num);
            }
        }
    }

    /** 测试synchronized锁是否具有重入性 */
    @Test
    public void testSynchronizedReentry() {
        new ReentryLock().method(5);
    }
}