package base.thread;

/**
 * 停止线程常用方式
 * @author hanzhi
 * @date 2021/7/25
 */
public class Thread_stop implements Runnable{

    private boolean flag = true;

    @Override
    public void run() {

        while (flag) {
            System.out.println("线程运行中....");
        }
    }

    public void stop() {
        this.flag = false;
    }
}
