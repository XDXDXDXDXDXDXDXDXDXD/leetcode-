package Base.thread;

/**
 * 线程优先级
 * @author hanzhi
 * @date 2021/7/25
 */
public class Thread_Priority implements Runnable{

    private int count = 0;
    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            synchronized (this) {
                count++;
            }
            System.out.println(Thread.currentThread().getName() +"----->"+ count);
        }
        System.out.println(Thread.currentThread().getName()+
                "-------->执行"+"优先级为"+Thread.currentThread().getPriority());
    }
}
