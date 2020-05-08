package 面试题;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
    public static void main(String args[]) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+" put...a");
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName()+" put...b");
                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName()+" put...c");
                blockingQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+" take "+blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+" take "+blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+" take "+blockingQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
