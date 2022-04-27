package Base.thread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 守护线程-->日志
 * @author hanzhi
 * @date 2021/7/27
 */
public class ThreadDaemon implements Runnable{

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Date date = new Date();
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = f.format(date);
            System.out.println("["+format+"]" + "当前运行:"+ Thread.currentThread().getName());

        }
    }
}
