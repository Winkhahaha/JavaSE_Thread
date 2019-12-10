package juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
抢占资源
 */
public class Demo11_Semaphore {
    public static void main(String args[]) {
        Semaphore semaphore = new Semaphore(1);  //模拟资源类有三个空车位
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("--------------------------");
                    System.out.println(Thread.currentThread().getName() + "-->抢占到车位");

                    TimeUnit.SECONDS.sleep(3);  //离开车位
                    System.out.println("--------------------------");
                    System.out.println(Thread.currentThread().getName() + "-->离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, i + "线程").start();
        }
    }
}
