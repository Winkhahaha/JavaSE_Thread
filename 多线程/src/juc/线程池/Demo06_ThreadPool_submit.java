package juc.线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;


public class Demo06_ThreadPool_submit {
    public static void main(String args[]) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newCachedThreadPool(); //一池n线程
        // 模拟有十个顾客来银行办理业务,目前有五个员工(线程)
        List<Future> futures = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(300);
                Future<String> future = pool.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "-->返回随机数");
                    return new Random().nextInt() + "";
                });
                futures.add(future);
            }
        } finally {
            pool.shutdown();
        }

        for (Future future : futures) {
            System.out.println(future.get());
        }
    }
}
