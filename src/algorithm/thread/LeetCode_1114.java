package algorithm.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Yanghz
 * @Since 2022/7/25
 * @Description
 */
public class LeetCode_1114 implements Runnable{

    private final AtomicInteger firstDone = new AtomicInteger(0);
    private final AtomicInteger secondDone = new AtomicInteger(0);


    public LeetCode_1114() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstDone.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        while (firstDone.get() != 1) {

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondDone.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {

        while (secondDone.get() != 1)  {

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    @Override
    public void run() {

    }
}
