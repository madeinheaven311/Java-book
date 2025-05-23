package com.d4c;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Demo {

    public Demo(){
        System.out.println("Demo");
    }
    static class ListResult{
        int length;
        List<Integer> lis;
        public ListResult(int length,List<Integer> lis){
            this.length = length;
            this.lis = lis;
        }
    }


    public ListResult findMax(int[] nums){
        if(nums == null || nums.length == 0){
            return new ListResult(0,new ArrayList<>());
        }
        int endIdx = 0;
        int maxLength = 1;
        int n = nums.length;
        int[] dp = new int[n];
        int[] dpIdx = new int[n];
        Arrays.fill(dp,1);
        Arrays.fill(dpIdx,-1);
        List<Integer> sequence;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[i] > nums[j] && dp[j] +1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    dpIdx[i] = j;
                    if(dp[i] > maxLength){
                        maxLength = dp[i];
                        endIdx = i;
                    }
                }
            }
        }


        int maxLen = 0;
        for(int len : dp){
            maxLen = Math.max(maxLen,len);
        }

        List<Integer> lis = new ArrayList<>();
        while(endIdx != -1){
            lis.add(nums[endIdx]);
            endIdx = dpIdx[endIdx];
        }
        return new ListResult(maxLength,reverse(lis));
    }

    private List<Integer> reverse(List<Integer> list){
        List<Integer> reverseLis = new ArrayList<>();
        for (int i = list.size() -1 ; i >=0 ; i--) {
            reverseLis.add(list.get(i));
        }
        return reverseLis;
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        int[] nums = {1,2,3};
        ListResult result = demo.findMax(nums);
        System.out.println(result.length);
        System.out.println(result.lis);
    }



}
