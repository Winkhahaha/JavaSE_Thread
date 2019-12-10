package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo05_多线程按顺序调用 {
    public static void main(String args[]) {
        Abc abc = new Abc();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {

                    abc.LoopA5();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {

                    abc.LoopB10();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {

                    abc.LoopC15();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
}

class Abc {
    private int num = 1;    // 1A,2B,3A
    private Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

    public void LoopA5() throws InterruptedException {
        // 打印A五次
        lock.lock();
        try {

            while (num != 1) {
                conditionA.await();
            }

            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "-->A");
            }

            // 精准通知
            num = 2;
            conditionB.signal();

        } finally {
            lock.unlock();
        }

    }

    public void LoopB10() throws InterruptedException {
        // 打印B十次
        lock.lock();
        try {

            while (num != 2) {
                conditionB.await();
            }

            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "-->B");
            }

            // 精准通知
            num = 3;
            conditionC.signal();

        } finally {
            lock.unlock();
        }

    }

    public void LoopC15() throws InterruptedException {
        // 打印B十次
        lock.lock();
        try {

            while (num != 3) {
                conditionC.await();
            }

            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "-->C");
            }

            // 精准通知
            num = 1;
            conditionA.signal();

        } finally {
            lock.unlock();
        }

    }

}
