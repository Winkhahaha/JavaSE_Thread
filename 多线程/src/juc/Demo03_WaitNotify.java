package juc;

/*
 两个线程一个变量
 一个线程对变量+1,另一个-1
 实现交替,来十轮
 */
public class Demo03_WaitNotify {
    public static void main(String args[]) {
        AirConditioner air = new AirConditioner();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    air.increment();    //生产产品,加法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者A").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    air.increment();    //生产产品,加法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者B").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    air.decrement();    //消费产品,减法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者C").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    air.decrement();    //消费产品,减法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者D").start();
    }
}

class AirConditioner {
    private static int num = 0;

    // 判断/干活/通知
    // 多线程交互/通信中必须要防止多线程的虚假唤醒,不能if,需要while
    public synchronized void increment() throws InterruptedException {
        while (num != 0) {
            this.wait();
        }
        // 生产者没有货,做一个
        num++;
        System.out.println(Thread.currentThread().getName() + "做" + num + "个");
        // 通知消费者线程,做好了一个来拿
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (num == 0) {
            // 没有货,消费者等待
            this.wait();
        }
        //有货,消费者拿走一个
        num--;
        System.out.println(Thread.currentThread().getName() + "拿" + num + "个");
        //通知生产者继续做
        this.notifyAll();
    }
}