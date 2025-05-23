package com.d4c;


abstract class Demo2 {

    static Demo  node = new Demo();

    public Demo2(){
        System.out.println("Demo2");
    }
    public static void A() {
        System.out.println("static");
    }

    abstract void B();

    void D(){

    }

    abstract void C();
}
