package edu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABCAlternate_9 {
/*
 * 三个线程ABC,依次打印ABCABCABC...
 */
	public static void main(String[] args) {
		AlternateDemo a = new AlternateDemo();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 20; i++) {
					a.loopA(i);
				}
			}
		},"A").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 20; i++) {
					a.loopB(i);
				}
			}
		},"B").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 20; i++) {
					a.loopC(i);
					System.out.println("-----------------------");
				}
			}
		},"C").start();
	}
}
class AlternateDemo{
	private int number = 1;//当前正在执行线程的标记
	private Lock lock =new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	
	//totalLoop:循环多少轮 10轮
	public void loopA(int totalLoop) {
		lock.lock();
		try {
			//判断
			if(number!=1) {
				condition1.await();
			}
			//打印
			for (int i = 1; i <=1; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop+"轮");
			}
			//唤醒别人
			number = 2;
			condition2.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
	
	public void loopB(int totalLoop) {
		lock.lock();
		try {
			//判断
			if(number!=2) {
				condition2.await();
			}
			//打印
			for (int i = 1; i <=1; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop+"轮");
			}
			//唤醒别人
			number = 3;
			condition3.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
	
	public void loopC(int totalLoop) {
		lock.lock();
		try {
			//判断
			if(number!=3) {
				condition3.await();
			}
			//打印
			for (int i = 1; i <=1; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop+"轮");
			}
			//唤醒别人
			number = 1;
			condition1.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
}