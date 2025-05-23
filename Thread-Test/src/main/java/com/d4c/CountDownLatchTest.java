package com.d4c;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/** @author JoeZhou */
public class CountDownLatchTest {

    /** 一共几匹马 */
    private final int NUM = 8;
    
    /** 创建门闩实例，并设置初始门闩数为8个 */
    private final CountDownLatch LATCH = new CountDownLatch(NUM);

    @SneakyThrows
    @Test
    public void testCountDownLatch() {

        // 使用主线程作为裁判线程
        System.out.printf("裁判线程: 比赛开始，一共%d匹马...\n", LATCH.getCount());

        // 开启8个线程，模拟8匹马
        for (int i = 1; i <= NUM; i++) {
            new Thread(() -> {
                try {
                    // 模拟没匹马的运动耗时
                    int spendTime = new Random().nextInt(5000);
                    TimeUnit.MILLISECONDS.sleep(spendTime);
                    // 马到了终点，拆除一层门闩
                    System.out.printf("%s 到达终点，耗时%d毫秒，",
                            Thread.currentThread().getName(), spendTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    // 拆掉1层门闩，即门闩计数器减1
                    LATCH.countDown();
                    System.out.printf("剩余 %d 匹马\n", LATCH.getCount());
                }
            }, i + "号马").start();
        }

        // 裁判线程挂起等待，门闩数为0时自动唤醒
        LATCH.await();
        System.out.println("裁判线程: 比赛结束...");
    }
}