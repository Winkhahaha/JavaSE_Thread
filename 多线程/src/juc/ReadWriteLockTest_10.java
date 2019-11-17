package edu.juc;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * ReadWriteLock:
 * 多个线程都聚在写操作(读写,写写),需要互斥
 * 读读不需要互斥
 */
public class ReadWriteLockTest_10 {
	public static void main(String[] args) {
		ReadWriteLockDemo r = new ReadWriteLockDemo();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				r.set(new Random().nextInt(50));
			}
		},"Write").start();
		
		for (int i = 0; i < 100; i++) {
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					r.get();
				}
			},"Reading").start();
		}
	}

}
class ReadWriteLockDemo{
	private int number = 0;
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	//读
	public void get() {
		lock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+":"+number);
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.readLock().unlock();//释放锁
		}
	}
	
	//写
	public void set(int number) {
		lock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+":"+number);
			this.number= number;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.writeLock().unlock();
		}
	}
}
