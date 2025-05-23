package com.d4c;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTest {

    @SneakyThrows
    @Test
    public void testConcurrentLinkedQueue() {
        // 创建ConcurrentLinkedQueue实例
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        // 向队列中添加元素
        concurrentLinkedQueue.add("赵四");
        // 弹出队列头
        System.out.println(concurrentLinkedQueue.peek());
        System.out.println(concurrentLinkedQueue.poll());
        // 此队列不阻塞，如果此队列为空，则弹出队列头操作直接返回null
        System.out.println(concurrentLinkedQueue.poll());
    }
}