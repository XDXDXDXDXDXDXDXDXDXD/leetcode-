package base.thread;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author hanzhi
 * @date 2021/7/24
 */
public class Example implements Runnable{

    private int count;

    private CountDownLatch countDownLatch;

    private List<String> list;

    public Example(CountDownLatch countDownLatch, List<String> list) {
        this.countDownLatch = countDownLatch;
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                count++;
                // 模拟http请求
                Thread.sleep(100);
                list.add("1");
                System.out.println("当前"+Thread.currentThread().getName()+"count为"+count);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
