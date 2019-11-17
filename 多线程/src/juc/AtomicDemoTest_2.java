package edu.juc;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * i++��ԭ��������,ʵ�����Ƿ�Ϊ������,��,��,д
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
	 * ԭ������ʹ��ԭ�ӱ���,java.util.concurrent
	 * 1.����volatile ��֤�ڴ�ɼ���
	 * 2.CAS(Compare-And-Swap)�㷨��֤���ݵ�ԭ����
	 * CAS�㷨��Ӳ�����ڲ��������������ݵ�֧��
	 * CAS����������������  
	 * �ڴ�ֵV
	 * Ԥ��ֵA
	 * ���µ�ֵB
	 * ���ҽ���V==A,V=B,���򲻽����κβ���
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

