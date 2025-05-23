package com.d4c;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JJJK {

    public static void main(String[] args) {
        String a = new String("ab"); // a 为一个引用
        String b = new String("ab"); // b 为另一个引用,对象的内容一样
        String c = a;
        String d = a;
        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找
        Integer e = new Integer(1);
        Integer f = e;
        Integer g = 111111;
        Integer h = 111111;

        Map<String, String> comMap = new ConcurrentHashMap<>();
        comMap.put("s", "sas");
        HashMap map = new HashMap();
        map.put(null, null);
        System.out.println(comMap.get("s"));
        System.out.println(map.get(null));


//        if (aa == bb) // true
//            System.out.println("aa==bb");
//        if (a == b) // false，非同一对象
//            System.out.println("a==b");
//        if (a.equals(b)) // true
//            System.out.println("aEQb");
//        if (d.equals(c)) // true
//            System.out.println("dEQc");
//        if (d==c) // true
//            System.out.println("d==c");
//        if (aa.equals(bb)) // true
//            System.out.println("aaEQbb");
//        if (42 == 42.0) { // true
//            System.out.println("true");
//        }
//        if(a.equals(c)){
//            System.out.println("gEQh");
//        }
//        if(g == h){
//            System.out.println("g==h");
//        }

    }

}
