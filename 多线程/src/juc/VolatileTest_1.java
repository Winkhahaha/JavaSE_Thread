package juc;

public class VolatileTest_1 {
	
	public static void main(String[] args) {
		ThreadDemo th = new ThreadDemo();
		new Thread(th).start();
		
		while(true) {
			//synchronized (th) {		
				if(th.isFlag()) {		//false
					System.out.println("..........");
					break;
				//}
			}
			
		}
	}

}
class ThreadDemo implements Runnable{
	/*
	 * volatile:当多个线程操作共享数据时,可以保障内存中的数据是可见的,及时刷新的
	 * 相较于synchronize是一种较轻量级的同步策略
	 * 注意:volatile不具备互斥性
	 * volatile不能保证变量的原子性
	 */
	private volatile boolean flag = false;

	@Override
	public void run() {
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		flag = true;
		System.out.println("flag="+isFlag());	//true
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
