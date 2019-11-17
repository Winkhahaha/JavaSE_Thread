package edu.juc;

import java.util.concurrent.CountDownLatch;

/*
 * 闭锁,在完成某些运算时,只有其他所有线程的运算全部完成,当前线程的运算才继续实现
 */
public class CountDownLatchTest_5 {
public static void main(String[] args) {
	final CountDownLatch latch = new CountDownLatch(5);
	LatchDemo ld = new LatchDemo(latch);
	
	Long start = System.currentTimeMillis();
	
	for (int i = 0; i < 5; i++) {
		new Thread(ld).start();
	}
	try {
		latch.await();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	Long end = System.currentTimeMillis();
	
	System.out.println("耗费时间为:"+(end - start)/1000);
}
}
class LatchDemo implements Runnable{
	private CountDownLatch latch;
	
	public LatchDemo(CountDownLatch latch) {
		this.latch=latch;
	}
	
	@Override
	public void run() {
		synchronized(this) {
			try {
				for (int i = 0; i < 50000; i++) {
					if(i%2==0) {
						System.out.println(i);
					}
				}
				
			} finally {
				latch.countDown();		//每次有线程完成后,必须减一
				
			}
			
		}
		}
}
