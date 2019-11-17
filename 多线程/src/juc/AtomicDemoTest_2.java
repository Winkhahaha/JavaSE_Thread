package edu.juc;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * i++的原子性问题,实际上是分为三步骤,读,改,写
 * int temp = i
 * i = i+1
 * syso:temp 
 */
public class AtomicDemoTest_2 {
public static void main(String[] args) {
	AtomicDemo ad = new AtomicDemo();
	for (int i = 0; i <10; i++) {
		new Thread(ad).start();
	}
}
}
class AtomicDemo implements Runnable{
	/*
	 * 原子问题使用原子变量,java.util.concurrent
	 * 1.它有volatile 保证内存可见性
	 * 2.CAS(Compare-And-Swap)算法保证数据的原子性
	 * CAS算法是硬件对于并发操作共享数据的支持
	 * CAS包含了三个操作数  
	 * 内存值V
	 * 预估值A
	 * 更新的值B
	 * 当且仅当V==A,V=B,否则不进行任何操作
	 */
	//private volatile int serialNumber = 0;
	private AtomicInteger serialNumber = new AtomicInteger();
	@Override
	public void run() {
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+":"+getSerialNumber());
	}
	public int getSerialNumber() {
		return serialNumber.getAndIncrement();
	}
	
	
}

