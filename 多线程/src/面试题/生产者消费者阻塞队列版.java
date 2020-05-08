package 面试题;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class 生产者消费者阻塞队列版 {
    public static void main(String args[]) throws InterruptedException {
        Resource resource = new Resource(new ArrayBlockingQueue<String>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":生产者线程启动");
            System.out.println();
            System.out.println();
            try {
                resource.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "生产者").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":消费者线程启动");
            System.out.println();
            System.out.println();
            try {
                resource.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "消费者").start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println();
        System.out.println();
        System.out.println(Thread.currentThread().getName()+":停止生成消费活动!");
        resource.stop();
    }
}

class Resource {
    private volatile boolean FLAG = true;    // 默认开启,进行生成+消费
    private AtomicInteger atomicInteger = new AtomicInteger();  // 默认值0
    BlockingQueue<String> blockingQueue = null;

    public Resource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void increment() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";    // ++i
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+":生产停止");
    }

    public void decrement() throws InterruptedException {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + ":等待超过两秒没有消费成功");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费队列" + result + "成功");
        }
    }

    public void stop() {
        this.FLAG = false;
    }
}