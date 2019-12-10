package juc;

import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Demo13_Blocking生产者消费者 {
    public static void main(String args[]) {
        Product product = new Product();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    product.increment();    //生产产品,加法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    product.decrement();    //消费产品,减法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者").start();
    }
}

class Product {
    private BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(1);  //队列大小1

    public void increment() throws InterruptedException {

        blockingQueue.put("商品");
        System.out.println(Thread.currentThread().getName() + "做" + blockingQueue.size() + "个");
        //队列满,阻塞,等待为空
    }

    public void decrement() throws InterruptedException {
        blockingQueue.take();
        System.out.println(Thread.currentThread().getName() + "拿之后剩" + blockingQueue.size() + "个");
        //队列空,阻塞,等待为满

    }

}