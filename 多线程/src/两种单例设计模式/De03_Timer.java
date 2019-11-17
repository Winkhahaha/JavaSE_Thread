package 两种单例设计模式;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class De03_Timer {

    public static void main(String args[]) throws InterruptedException {

        // 创建计时器
        Timer t = new Timer();

        // 指定时间安排指定任务
        // 任务   执行的时间   过多长毫秒重复执行
        t.schedule(new MyTimerTask(),new Date(119,11,17,16,31,15),3000);
        // 隔一秒打印时间
        while(true){
            Thread.sleep(1000);
            System.out.println(new Date());
        }
    }
}
class MyTimerTask extends TimerTask{

    @Override
    public void run() {
        System.out.println("起床背英语!");
    }
}
