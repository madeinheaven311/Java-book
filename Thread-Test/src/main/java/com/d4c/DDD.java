package com.d4c;

public interface DDD {
    default void A(){
        System.out.println("default");
    }

    static void D(){
        System.out.println("static");
    }
    void C();
    void B();
}
