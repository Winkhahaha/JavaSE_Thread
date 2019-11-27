package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * ���ڽ�����̰߳�ȫ����ķ�ʽ
 * ͬ�������,ͬ������synchronize	��ʽ��
 * jdk1.5�� ͬ����Lock	��ʾ����Ҫͨ��lock��������,unlock�ͷ���
 */
public class LockTicketTest_7 {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(ticket, "1�Ŵ���").start();
		new Thread(ticket, "2�Ŵ���").start();
		new Thread(ticket, "3�Ŵ���").start();
	}
}

class Ticket implements Runnable {
	private int tick = 100;
	private Lock lock = new ReentrantLock();

	@Override
	public void run() {
		while (true) {
			lock.lock();
			try {
				if (tick > 0) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "�����Ʊ,��ƱΪ:" + --tick);
				}
			} finally {
				lock.unlock();	//�ͷ���
			}
		}
	}
}
