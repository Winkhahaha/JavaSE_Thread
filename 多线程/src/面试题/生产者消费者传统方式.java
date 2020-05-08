package 面试题;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class 生产者消费者传统方式 {
    public static void main(String args[]){
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"生产者").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"消费者").start();
    }
}

class ShareData {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        while (num != 0){
            // 有货,生产停止
            condition.await();
        }
        // 生产
        num++;
        System.out.println(Thread.currentThread().getName()+" 生产 "+num);
        // 唤醒消费者线程,消费
        condition.signalAll();
        lock.unlock();
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        while (num == 0){
            // 没货,停止消费
            condition.await();
        }
        // 消费
        num--;
        System.out.println(Thread.currentThread().getName()+" 消费 "+num);
        // 唤醒生产者线程,生产
        condition.signalAll();
        lock.unlock();
    }
}
