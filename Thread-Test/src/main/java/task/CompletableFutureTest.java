package task;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/** @author JoeZhou */
public class CompletableFutureTest {

    @SneakyThrows
    private String taskA() {
        TimeUnit.SECONDS.sleep(1L);
        System.out.println("1s: taskA执行完毕");
        return "A";
    }

    @SneakyThrows
    private String taskB() {
        TimeUnit.SECONDS.sleep(2L);
        System.out.println("2s: taskB执行完毕");
        return "B";
    }

    @SneakyThrows
    private String taskC() {
        TimeUnit.SECONDS.sleep(3L);
        System.out.println("3s: taskC执行完毕");
        return "C";
    }

    @SneakyThrows
    private void taskD() {
        TimeUnit.SECONDS.sleep(1L);
        System.out.println("1s: taskD执行完毕");
    }

    @SneakyThrows
    private void taskE() {
        TimeUnit.SECONDS.sleep(2L);
        System.out.println("2s: taskE执行完毕");
    }

    @SneakyThrows
    private void taskF() {
        TimeUnit.SECONDS.sleep(3L);
        System.out.println("3s: taskF执行完毕");
    }

    /** 使用CompletableFuture异步执行6个任务，计算总耗时 */
    @SneakyThrows
    @Test
    public void testTaskByCompletableFuture() {
        long start = System.currentTimeMillis();

        // supplyAsync(): 执行三个带返回值的异步任务
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(this::taskA);
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(this::taskB);
        CompletableFuture<String> futureC = CompletableFuture.supplyAsync(this::taskC);

        // runAsync(): 执行三个不带返回值的异步任务
        CompletableFuture<Void> futureD = CompletableFuture.runAsync(this::taskD);
        CompletableFuture<Void> futureE = CompletableFuture.runAsync(this::taskE);
        CompletableFuture<Void> futureF = CompletableFuture.runAsync(this::taskF);

        //CompletableFuture<Void> futureG = CompletableFuture.completedFuture(this::taskF);

        // 任务汇总: 当六个future全部完成之后，汇总到一个新的future实例中
        CompletableFuture<Void> future = CompletableFuture.allOf(
                futureA, futureB, futureC, futureD, futureE, futureF);

        // 让任务汇总工作插主线程的队
//        future.join();
        future.get();
        Thread.sleep(10000L);

        // 当futureA/futureB/futureC任务均执行完毕后，获取其返回值
        if (futureA.isDone() && futureB.isDone() && futureC.isDone()) {
            System.out.println("taskA返回值: " + futureA.get());
            System.out.println("taskB返回值: " + futureB.get());
            System.out.println("taskC返回值: " + futureC.get());
        }

		long end = System.currentTimeMillis();  

		// 计算总耗时
		System.out.println("耗时:" + (end - start));
    }
}