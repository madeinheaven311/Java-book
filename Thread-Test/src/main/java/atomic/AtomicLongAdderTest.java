package atomic;

import org.junit.Test;

import java.util.concurrent.atomic.LongAdder;

/** @author JoeZhou */
public class AtomicLongAdderTest {

    /** 创建一个原子的LongAdder实例，初始值为0 */
    private final LongAdder num = new LongAdder();

    @Test
    public void testApi() {

        // LongAdder允许直接输出
        System.out.println("num: " + num);

        num.add(5);
        System.out.println("after +5: " + num);

        num.increment();
        System.out.println("after +1: " + num);

        num.decrement();
        System.out.println("after -1: " + num);
    }
}