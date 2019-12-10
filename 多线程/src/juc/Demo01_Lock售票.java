package juc;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
  高内聚低耦合的前提下,线程        操作(对外暴露的调用方法)           资源类(Ticket)
 */
public class Demo01_Lock售票 {
    public static void main(String args[]) {
        Ticket2 ticket2 = new Ticket2();

        new Thread(() -> {
            for (int i = 0; i <= 400; i++) {
                ticket2.saleTicket();
            }
        }, "A").start();    //线程就绪,等待cpu和操作系统的底层调度通知

        new Thread(() -> {
            for (int i = 0; i <= 400; i++) {
                ticket2.saleTicket();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i <= 400; i++) {
                ticket2.saleTicket();
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i <= 400; i++) {
                ticket2.saleTicket();
            }
        }, "D").start();

    }
}

// 资源类
class Ticket2 {
    private static int number = 350;
    private Lock lock = new ReentrantLock();

    // 操作
    public void saleTicket() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "-->卖出第:" + number-- + "张票,还剩下:" + number + "张");
            }
        } finally {
            lock.unlock();
        }
    }
}
