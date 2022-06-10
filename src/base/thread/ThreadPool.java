package base.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过线程池获取线程
 * @author hanzhi
 * @date 2021/8/5
 */
public class ThreadPool {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 4; i++) {
            MyThreadImpl thread = new MyThreadImpl();
            pool.execute(thread);
        }
        pool.shutdown();
    }
}
