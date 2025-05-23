package com.d4c;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/** @author JoeZhou */
public class AtomicTest {

    /** 普通的Integer变量，用于测试自增是否线程安全 */
    private Integer num = 0;



    /** 原子的Integer变量，用于测试自增是否线程安全 */
    private final AtomicInteger atomicNum = new AtomicInteger(0);

    /** 测试: 普通的num的自增是否是线程安全的？原子的num的自增是否是线程安全的？ */
    @Test
    @SneakyThrows
    public void testAtomicOperate() {

        // 开启100个线程
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                // 每个线程执行100次普通num自增和原子num自增
                for (int j = 0; j < 100; j++) {
                    num++;
                    atomicNum.incrementAndGet();
                }
            }).start();
        }

        // 防止主线程在子线程执行自增操作完毕之前输出
        TimeUnit.SECONDS.sleep(10L);

        // 普通num自增：最后可能会丢失一批数据，最终值达不到1W。
        // 原子num自增：最后不会丢失任何数据，最终值一定为1W。
        System.out.println("num: " + num);
        System.out.println("atomicNum: " + atomicNum);




    }
}