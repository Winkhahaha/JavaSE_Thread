package edu.juc.�̳߳�;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * �̳߳�:�ṩ��һ���̶߳���,�����б��������еȴ�״̬���߳�,�����˴����������̵߳Ķ��⿪��
 * �������Ӧ���ٶ�
 * ��.�̳߳ص���ϵ�ṹ
 * juc���е�.Executor:�����̵߳�ʹ�ú͵��ȵĸ��ӿ�
 * ----**ExecutorService**�ӽӿ�:�̳߳ص���Ҫ�ӿ�
 * -----------ThreadPoolExecutor�̳߳ص�ʵ����
 * -----------ScheduleExecutorService�ӽӿ�,�����̵߳ĵ���
 * ----------------ScheduleThreadPoolExcutor:�̳���ThreadPoolExecutor,ʵ����ScheduleExecutorService�ӿ�
 * 
 * ��.������:Executors
 * newFixedThreadPool:�����̶���С���̳߳�
 * newCaschedThreadPool:�����̳߳�,�̳߳ص��������̶�,���Ը��������Զ��ĸ�������
 */
public class ThreadPool_13 {
	public static void main(String[] args) throws Exception, Exception {
//		new Thread(t).start();
//		new Thread(t).start();
		
		//1.�����̳߳�
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
//		//2.Ϊ�̳߳��е��̷߳�������
//		for (int i = 0; i < 10; i++) {
//			pool.submit(t);
//		}
//		
//		//3.�ر��̳߳�
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
