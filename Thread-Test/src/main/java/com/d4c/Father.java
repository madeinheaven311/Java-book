package com.d4c;


import lombok.Data;


public class Father {


    public Father(){
        System.out.println("用了父类构造器"+this.getClass());
    }

    public void method(){

        System.out.println("父亲");

    }
    static {

        System.out.println("我是父亲");
    }
    public void IntMethod(int a){

        System.out.println("a");

    }

}
