package com.d4c;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/** @author JoeZhou */
public class SellTicketAsyncTest {

    /** 模拟售票厅售票程序 */
    private static class Ticket implements Runnable {

        /** 票号，多线程共享资源 */
        private static int ticketNo;

        @SneakyThrows
        @Override
        public void run() {



            while (true) {
                // 每隔1秒钟卖一张票
                TimeUnit.SECONDS.sleep(1L);
                if( Thread.currentThread().getName().equals("江南售票厅")){

                    sellTicket();

                }else if(Thread.currentThread().getName().equals("江北售票厅")){

                        System.out.printf("%s卖了%d号的票\n", Thread.currentThread().getName(), ++ticketNo);

                }


            }
        }

        /** 卖票方法 */
        private synchronized void sellTicket() {
            System.out.printf("%s卖了%d号的票\n", Thread.currentThread().getName(), ++ticketNo);
        }
    }

    @Test
    @SneakyThrows
    public void testSellTicket() {
    
        // 创建线程类
        Runnable runnable = new Ticket();

        // 创建两个线程
        Thread t01 = new Thread(new Ticket(), "江南售票厅");
        Thread t02 = new Thread(new Ticket(), "江北售票厅");

        // 启动两个线程并插队执行
        t01.start();
        t02.start();
        t01.join();
        t02.join();

        System.out.println("主线程结束");
    }
}