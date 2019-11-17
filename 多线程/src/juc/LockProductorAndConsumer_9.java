package edu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 生产者消费者案例
 */
public class LockProductorAndConsumer_9 {
	public static void main(String[] args) {
			Clerk2 c2 = new Clerk2();
			Productor2 p2 = new Productor2(c2);
			Consumer2 con2 = new Consumer2(c2);
			new Thread(p2,"生产者A").start();
			new Thread(con2,"消费者B").start();
			new Thread(p2,"生产者C").start();
			new Thread(con2,"消费者D").start();
	}
}

// 店员
class Clerk2 {
	private int product = 0;
	private Lock lock = new ReentrantLock(); 
	private Condition condition = lock.newCondition();

	// 进货
	public void get() {
		lock.lock();
		try {
			
			while (product >=1) {//为了避免虚假唤醒问题,应该总是使用在循环中
				System.out.println("产品已满");
				try {
					//this.wait();
					condition.await();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			} 
			System.out.println(Thread.currentThread().getName() + "进货,剩余:" + ++product);
			//this.notifyAll();
			condition.signalAll();
		}finally {
			lock.unlock();
		}
		
	}

	// 卖货
	public void sale() {
		lock.lock();
		try {
			while (product <= 0) {
				System.out.println("缺货");
				try {
					//this.wait();
					condition.await();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			} 
			System.out.println(Thread.currentThread().getName() + "卖货,剩余:" + --product);
			//this.notifyAll();
			condition.signalAll();
		}finally {
			lock.unlock();
		}
	}
}

class Productor2 implements Runnable {
	private Clerk2 clerk;

	public Productor2(Clerk2 clerk) {
		super();
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			clerk.get();
		}
	}

}

// 消费者
class Consumer2 implements Runnable {
	private Clerk2 clerk;

	public Consumer2(Clerk2 clerk) {
		super();
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			clerk.sale();
		}
	}

}
