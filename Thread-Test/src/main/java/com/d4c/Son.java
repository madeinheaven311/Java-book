package com.d4c;


import java.util.Stack;

public class Son extends Father{


    final int a = 1;

    public Son() {
        System.out.println("用的儿子的构造器"+this.getClass());
    }


    static {

        System.out.println("我是儿子");
    }

    @Override
    public void method( ) {
        System.out.println("儿子");
    }


    public void method2(){
        super.method();
    }


    public void method3(){

        System.out.println(" ");


    }



    public static void main(String[] args) {

        Father a = new Father();
        Son b = new Son();
        Father c = new Son();

        System.out.println(a == b);
        ((Son) c).method2();

    }
}
