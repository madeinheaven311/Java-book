package com.d4c;

public class Demo3 extends Demo2 {

    static int a = 1;

    static Node node = new Node();
    @Override
    void B() {

    }

    public Demo3(){
        System.out.println("Demo3");
    }
    public static A makeA(){
        return new A(){

            @Override
            public void m() {
                System.out.println("m哒!!!");
            }
        };
    }

    public static X makeX(){
        return new X(){};
    }
    @Override
    void C() {

    }

    static class M {
        public void M() {
            System.out.println( "MMM" );
        }
    }
    class InnerClass {

        public final static String g = "aaaa";

        private void A() {
            System.out.println("static");
        }

        int c = a;

    }

    static class StaticClass{
        int b = a;
    }

    public static void main(String[] args) {
        Demo3 demo3 = new Demo3();

        demo3.B();
        Demo2.A();
        InnerClass innerClass = demo3.new InnerClass();
        innerClass.A();
        Demo3.makeA().m();
        Demo3.makeX().xx();
    }
}
