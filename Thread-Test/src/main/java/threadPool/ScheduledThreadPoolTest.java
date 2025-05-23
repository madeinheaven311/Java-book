package threadPool;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** @author JoeZhou */
public class ScheduledThreadPoolTest {

    @SneakyThrows
    @Test
    public void testSchedule() {
        // 获取当前时间中的秒
        System.out.println(Calendar.getInstance().get(Calendar.SECOND));
        // 创建一个定时线程池
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        // 延迟2秒后执行任务
        executorService.schedule(() -> {
            System.out.println(Calendar.getInstance().get(Calendar.SECOND));
        }, 2, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(20L);
    }

    @SneakyThrows
    @Test
    public void testScheduleAtFixedRate() {
        // 获取当前时间中的秒
        System.out.println(Calendar.getInstance().get(Calendar.SECOND));
        // 创建一个定时线程池
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        // 延迟2秒后执行任务，然后从上一个任务 开始 执行时始计算，每隔3秒执行一次
        executorService.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.SECONDS.sleep(2L);
                System.out.println(Calendar.getInstance().get(Calendar.SECOND));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 2, 3, TimeUnit.SECONDS);

        System.out.println(System.in.read());
    }

    @SneakyThrows
    @Test
    public void testScheduleWithFixedDelay() {
        // 获取当前时间中的秒
        System.out.println(Calendar.getInstance().get(Calendar.SECOND));
        // 创建一个定时线程池
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        // 延迟2秒后执行任务，然后从上一个任务执行 结束 时始计算，每隔3秒执行一次
        executorService.scheduleWithFixedDelay(() -> {
            try {
                TimeUnit.SECONDS.sleep(2L);
                System.out.println(Calendar.getInstance().get(Calendar.SECOND));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 2, 3, TimeUnit.SECONDS);

        System.out.println(System.in.read());
    }
}