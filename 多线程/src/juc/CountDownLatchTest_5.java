package edu.juc;

import java.util.concurrent.CountDownLatch;

/*
 * ����,�����ĳЩ����ʱ,ֻ�����������̵߳�����ȫ�����,��ǰ�̵߳�����ż���ʵ��
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
	
	System.out.println("�ķ�ʱ��Ϊ:"+(end - start)/1000);
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
				latch.countDown();		//ÿ�����߳���ɺ�,�����һ
				
			}
			
		}
		}
}
