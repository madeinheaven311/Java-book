package com.d4c;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> result = new ArrayList<>();
        
        int carry = 0; // 进位
        int i = num.length - 1;
        
        while (i >= 0 || k > 0 || carry > 0) {
            int digit = carry; // 当前位的值等于进位值
            
            if (i >= 0) {
                digit += num[i]; // 加上当前数字的值
            }
            if (k > 0) {
                digit += k % 10; // 加上 k 的最低位
                k /= 10; // 去掉 k 的最低位
            }
            
            result.add(0, digit % 10); // 将当前位的值加入结果列表
            carry = digit / 10; // 计算进位
            
            i--; // 处理下一位
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] num = {1, 2, 0, 0};
        int k = 34;
        
        List<Integer> result = addToArrayForm(num, k);
        
        System.out.println("数组形式 num ：" + arrayToString(num));
        System.out.println("整数 k ：" + k);
        System.out.println("相加后的数组形式：" + result);
    }
    
    // 辅助方法，将数组转换为字符串形式
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
