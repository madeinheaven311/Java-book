package com.d4c;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Exception extends Throwable {


    public static class TestException  implements Runnable{

        int num = 0;

        @SneakyThrows
        @Override
        public void run() {
            if(Thread.currentThread().getName().equals("江南售票厅")){

                synchronized (this){

                        System.out.println(10 / 0);

                }

            } else if (Thread.currentThread().getName().equals("江北售票厅")) {
                TimeUnit.SECONDS.sleep(5L);
                synchronized (this) {

                        System.out.println("我拿到锁了");

                }
            }

        }
    }

    @Test
    @SneakyThrows
    public void Test01(){

        // 创建线程类
        Runnable runnable = new TestException();

        // 创建两个线程
        Thread t01 = new Thread(runnable ,"江南售票厅");
        Thread t02 = new Thread(runnable, "江北售票厅");

        // 启动两个线程并插队执行
        t01.start();
        t02.start();
        t01.join();
        t02.join();

        System.out.println("主线程结束");


    }
}
