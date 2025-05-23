package com.d4c;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/** @author JoeZhou */
public class CollectionsTest {


    HashMap<Integer, Integer> map = new HashMap<>();

    @Test
    public void testSynchronizedList() {
        // 将异步的ArrayList集合转换为同步的List集合
        map.put(1,1);
        map.get(1);
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        list.add("赵四");
        System.out.println(list.get(0));
    }
}