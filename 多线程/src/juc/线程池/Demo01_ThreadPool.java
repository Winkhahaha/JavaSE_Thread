package juc.线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * 线程池:提供了一个线程队列,队列中保存着所有等待状态的线程,避免了创建于销毁线程的额外开销
 * 提高了响应的速度
 * 二.线程池的体系结构
 * juc包中的.Executor:负责线程的使用和调度的根接口
 * ----**ExecutorService**子接口:线程池的主要接口
 * -----------ThreadPoolExecutor线程池的实现类
 * -----------ScheduleExecutorService子接口,用于线程的调度
 * ----------------ScheduleThreadPoolExcutor:继承了ThreadPoolExecutor,实现了ScheduleExecutorService接口
 *
 * 三.工具类:Executors
 * newFixedThreadPool:创建固定大小的线程池
 * newCaschedThreadPool:缓存线程池,线程池的数量不固定,可以根据需求自动的更改数量
 */
public class Demo01_ThreadPool {
    public static void main(String args[]) throws InterruptedException {
        //ExecutorService pool = Executors.newFixedThreadPool(5); //一个线程池中五个受理线程
        //ExecutorService pool = Executors.newSingleThreadExecutor(); //一个线程池中一个受理线程
        ExecutorService pool = Executors.newCachedThreadPool(); //一池n线程
        // 模拟有十个顾客来银行办理业务,目前有五个员工(线程)
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(300);
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "-->办理业务");
                });
            }
        } finally {
            pool.shutdown();
        }
    }
}
