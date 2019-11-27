package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 用于解决多线程安全问题的方式
 * 同步代码块,同步方法synchronize	隐式锁
 * jdk1.5后 同步锁Lock	显示锁需要通过lock方法上锁,unlock释放锁
 */
public class LockTicketTest_7 {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(ticket, "1号窗口").start();
		new Thread(ticket, "2号窗口").start();
		new Thread(ticket, "3号窗口").start();
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
					System.out.println(Thread.currentThread().getName() + "完成售票,余票为:" + --tick);
				}
			} finally {
				lock.unlock();	//释放锁
			}
		}
	}
}
