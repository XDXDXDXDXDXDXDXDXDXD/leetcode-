package base.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 可重入锁
 * @author hanzhi
 * @date 2021/8/1
 */
public class ThreadLock implements Runnable {

    int tickNum = 1000;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (tickNum > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "得到了第" + tickNum-- + "张票");
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
