package juc.线程池;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPoolTest_14 {
	/*
	 * 线程调度
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService pool= Executors.newScheduledThreadPool(5);
		
		for (int i = 0; i < 10; i++) {
			Future<Integer> result=pool.schedule(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int num = new Random().nextInt(50);
					System.out.println(Thread.currentThread().getName()+":"+num);
					return num;
				}
			}, 1, TimeUnit.SECONDS);
			System.out.println(result.get());
		}
		
		pool.shutdown();
		
	}
}
