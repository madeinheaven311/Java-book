package com.d4c;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

/** @author JoeZhou */
public class CopyOnWriteArrayListTest {

    @Test
    public void testCopyOnWriteArrayList() {
        // 创建写时复制List容器
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        // add方法是添加了 `synchronized` 锁的，更安全
        copyOnWriteArrayList.add("赵四");
        // get方法是不添加 `synchronized` 锁的，更效率
        System.out.println(copyOnWriteArrayList.get(0));
    }
}