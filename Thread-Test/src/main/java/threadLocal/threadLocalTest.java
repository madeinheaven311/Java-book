package threadLocal;

public class threadLocalTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("hello");
        System.out.println(threadLocal.get());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(threadLocal.get());
            }
        });
        thread.start();
        thread.join();
    }
}
