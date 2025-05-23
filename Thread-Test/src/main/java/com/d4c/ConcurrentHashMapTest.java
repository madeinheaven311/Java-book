package com.d4c;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/** @author JoeZhou */
public class ConcurrentHashMapTest {
    @Test
    public void testConcurrentHashMap() {

        List<Integer> list = new ArrayList<>();

        Integer B = 1;
        int c = 1;
        int[] a = new int[5];

        // 创建ConcurrentHashMap实例，初始容量为3，默认16
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>(3);
        // 存储键值对
        concurrentHashMap.put("name", "赵四");
        concurrentHashMap.put("age", "48");
        concurrentHashMap.put("gender", "男");
        // 获取值
        concurrentHashMap.get("name");
        // 遍历
        concurrentHashMap.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}