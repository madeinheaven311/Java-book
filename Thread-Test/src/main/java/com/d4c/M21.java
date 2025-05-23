package com.d4c;

import java.util.ArrayList;
import java.util.Scanner;

public class M21 {

   public static  boolean isPrime( int num){

       if(num <= 1 )return false;
       if(num <= 3)return  true;
       if(num % 2==0 || num% 3==0)return false;
       for( int i=5;i*i <=num;i+=6 ){

           if(num % i==0|| num% (i+2)==0){

               return false;
           }
       }
       return true;
   }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];
        for( int i = 0 ; i < n ; i++ ){

            arr[i] = scanner.nextInt();

        }


        int mergedCount = 0;
        while(true){
            boolean merged = false;

            for (int i = 0; i < n-1; i++) {

            }

        }
    }

}
