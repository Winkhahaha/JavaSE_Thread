package edu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
 * �����̵߳ķ�ʽ3:ʵ��Callable�ӿ�,�����runnable�ӿ�,���������з���ֵ,���ҿ����׳��쳣
 * //ִ��callable,��ҪFutureTaskʵ�����֧��,���ڽ��ռ�����,FutureTask��Future�ӿڵ�ʵ����
 */
public class CallableTest_6 {
	
	public static void main(String[] args) {
		ThreadDemo2 td = new ThreadDemo2();
		FutureTask<Integer> result = new FutureTask<>(td);
		new Thread(result).start();
		
		//�����߳��������(��������߳̽�����),����FutureTaskҲ�������ڱ�������
		//�ײ�ʵ��(runnable,future)�����֧�̰߳���һ���������
		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (ExecutionException e) {
			
			e.printStackTrace();
		}
		
	}

}
class ThreadDemo2 implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		int sum =0;
		for (int i = 0; i <= 100; i++) {
			sum+=i;
		}
		
		return sum;
	}
}
//class ThreadDemo implements Runnable{
//	@Override
//	public void run() {
//	}
//}