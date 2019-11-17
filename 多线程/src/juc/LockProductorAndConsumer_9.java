package edu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * �����������߰���
 */
public class LockProductorAndConsumer_9 {
	public static void main(String[] args) {
			Clerk2 c2 = new Clerk2();
			Productor2 p2 = new Productor2(c2);
			Consumer2 con2 = new Consumer2(c2);
			new Thread(p2,"������A").start();
			new Thread(con2,"������B").start();
			new Thread(p2,"������C").start();
			new Thread(con2,"������D").start();
	}
}

// ��Ա
class Clerk2 {
	private int product = 0;
	private Lock lock = new ReentrantLock(); 
	private Condition condition = lock.newCondition();

	// ����
	public void get() {
		lock.lock();
		try {
			
			while (product >=1) {//Ϊ�˱�����ٻ�������,Ӧ������ʹ����ѭ����
				System.out.println("��Ʒ����");
				try {
					//this.wait();
					condition.await();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			} 
			System.out.println(Thread.currentThread().getName() + "����,ʣ��:" + ++product);
			//this.notifyAll();
			condition.signalAll();
		}finally {
			lock.unlock();
		}
		
	}

	// ����
	public void sale() {
		lock.lock();
		try {
			while (product <= 0) {
				System.out.println("ȱ��");
				try {
					//this.wait();
					condition.await();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			} 
			System.out.println(Thread.currentThread().getName() + "����,ʣ��:" + --product);
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

// ������
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
