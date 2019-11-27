package juc;

/*
 * 生产者消费者案例
 */
public class SynProductorAndConsumer_8 {
	public static void main(String[] args) {
			Clerk c = new Clerk();
			Productor p = new Productor(c);
			Consumer con = new Consumer(c);
			new Thread(p,"生产者A").start();
			new Thread(con,"消费者B").start();
			new Thread(p,"生产者C").start();
			new Thread(con,"消费者D").start();
	}
}

// 店员
class Clerk {
	private int product = 0;

	// 进货
	public synchronized void get() {
		while (product >=1) {//为了避免虚假唤醒问题,应该总是使用在循环中
			System.out.println("产品已满");
			try {
				this.wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		} 
			System.out.println(Thread.currentThread().getName() + "进货,剩余:" + ++product);
			this.notifyAll();
		
	}

	// 卖货
	public synchronized void sale() {
		while (product <= 0) {
			System.out.println("缺货");
			try {
				this.wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		} 
			System.out.println(Thread.currentThread().getName() + "卖货,剩余:" + --product);
			this.notifyAll();
	}
}

class Productor implements Runnable {
	private Clerk clerk;

	public Productor(Clerk clerk) {
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

// 消费者
class Consumer implements Runnable {
	private Clerk clerk;

	public Consumer(Clerk clerk) {
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
