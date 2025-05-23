package com.d4c;

public class LoaderTestJunior extends LoaderTest{

    public LoaderTestJunior(){
        System.out.println("junior creator");
    }

    static {

        System.out.println("junior static");

    }

    public static void main(String[] args) {
        LoaderTestJunior loaderTestJunior = new LoaderTestJunior();
    }

}
