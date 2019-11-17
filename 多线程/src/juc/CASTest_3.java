package edu.juc;
/*
 * ģ��CAS�㷨
 */
public class CASTest_3 {
public static void main(String[] args) {
	final CompareAndSwep cas = new CompareAndSwep();
	for (int i = 0; i < 10; i++) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int expectValue = cas.get();
				boolean b =cas.compareAndSet(expectValue, (int)(Math.random()*101));
			}
		}).start();
	}
}
}
class CompareAndSwep{
	private int value;
	
	//��ȡ�ڴ�ֵ
	public synchronized int get() {
		return value;
	}
	//�Ƚ�
	public synchronized int compareAndSwep(int expectValue,int newValue) {
		int oldValue = value;
		if(oldValue == expectValue) {
			this.value= newValue;
		}
		return oldValue;
	}
	
	//����
	public synchronized boolean compareAndSet(int expectValue,int newValue) {
		return expectValue == compareAndSwep(expectValue, newValue);
	}
}
