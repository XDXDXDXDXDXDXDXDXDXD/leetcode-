package base.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author hanzhi
 * @date 2021/7/23
 */
public class MyTimerTask {

    public void start2Task() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时任务1开始" + new Date());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("定时任务1完成" + new Date());
            }
        };
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时任务2开始" + new Date());
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000, 3000);
        timer.schedule(timerTask2, 1000, 3000);
    }
}
