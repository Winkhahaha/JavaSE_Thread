package edu.juc;
/*
 * 模拟CAS算法
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
	
	//获取内存值
	public synchronized int get() {
		return value;
	}
	//比较
	public synchronized int compareAndSwep(int expectValue,int newValue) {
		int oldValue = value;
		if(oldValue == expectValue) {
			this.value= newValue;
		}
		return oldValue;
	}
	
	//设置
	public synchronized boolean compareAndSet(int expectValue,int newValue) {
		return expectValue == compareAndSwep(expectValue, newValue);
	}
}
