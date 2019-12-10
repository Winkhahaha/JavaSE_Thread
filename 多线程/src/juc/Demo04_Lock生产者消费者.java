package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo04_Lock生产者消费者 {
    public static void main(String args[]) {
    Case c = new Case();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    c.increment();    //生产产品,加法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者A").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    c.increment();    //生产产品,加法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者B").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    c.decrement();    //消费产品,减法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者C").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    c.decrement();    //消费产品,减法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者D").start();
    }
}

/*
Condition可替换wait,notify等
-->await,signal
 */
class Case {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "做" + num + "个");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "拿" + num + "个");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
