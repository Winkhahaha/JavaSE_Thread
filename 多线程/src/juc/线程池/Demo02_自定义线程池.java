package juc.线程池;

import java.util.concurrent.*;

public class Demo02_自定义线程池 {
    public static void main(String args[]) throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors()); //cpu核数
        ExecutorService pool = new ThreadPoolExecutor(
                2,
                5,      // maximumPoolSize=cpu核数+1or2
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            // 整个最大容纳数等于maximumPoolSize+队列容量=5+3=8
            for (int i = 0; i < 10; i++) {
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "-->办理业务");
                });
            }
        } finally {
            pool.shutdown();
        }
    }
}
