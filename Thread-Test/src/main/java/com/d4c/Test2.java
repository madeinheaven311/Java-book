package com.d4c;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Test2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            c[i] = scanner.nextInt();
        }


        scanner.close();


        System.out.println(maxMatch( a , b , c));
    }


    public static int maxMatch( int[] a , int[] b , int[] c  ){

        Map<Integer , Integer> sumCount = new HashMap<>();
        for (int i = 0; i < a.length; i++) {

            int sum = a[i] + b[i];
            sumCount.put(sum , sumCount.getOrDefault(sum , 0) + 1);

        }

        Map<Integer , Integer> cCount  =new HashMap<>();
        for( int value : c ){

            cCount.put(value , cCount.getOrDefault(value , 0) + 1);

        }

        int matches = 0;

        for( int value : cCount.keySet()){
            if( sumCount.containsKey(value) ){

                matches += Math.min( sumCount.get(value) , cCount.get(value) );

            }

        }
        return matches;
    }

}