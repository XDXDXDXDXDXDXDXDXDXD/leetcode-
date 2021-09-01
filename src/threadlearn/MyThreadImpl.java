package threadlearn;

/**
 * @author hanzhi
 * @date 2021/7/23
 */
public class MyThreadImpl implements Runnable {


    @Override
    public void run() {
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + "-->开始执行" + System.currentTimeMillis());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
