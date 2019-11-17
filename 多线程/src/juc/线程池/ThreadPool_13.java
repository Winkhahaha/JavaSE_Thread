package edu.juc.线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
public class ThreadPool_13 {
	public static void main(String[] args) throws Exception, Exception {
//		new Thread(t).start();
//		new Thread(t).start();
		
		//1.创建线程池
		ExecutorService pool= Executors.newFixedThreadPool(5);
		List<Future<Integer>> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Future<Integer> future = 
					pool.submit(new Callable<Integer>() {
						@Override
						public Integer call() throws Exception {
							int sum =0;
							for (int i = 1; i <=100; i++) {
								sum=sum+i;
							}
							return sum;
						}
					});
					list.add(future);
		}
		pool.shutdown();
		for (Future<Integer> future : list) {
			System.out.println(future.get());
		}
		
//		ThreadDemo6 t = new ThreadDemo6();
		//Runnable
//		//2.为线程池中的线程分配任务
//		for (int i = 0; i < 10; i++) {
//			pool.submit(t);
//		}
//		
//		//3.关闭线程池
//		pool.shutdown();
	}
}
class ThreadDemo6 implements Runnable{
	private int i =0;
	@Override
	public void run() {
		while(i<100) {
			System.out.println(Thread.currentThread().getName()+":"+i++);
		}
	}
}
