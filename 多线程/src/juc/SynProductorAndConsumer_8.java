package juc;

/*
 * �����������߰���
 */
public class SynProductorAndConsumer_8 {
	public static void main(String[] args) {
			Clerk c = new Clerk();
			Productor p = new Productor(c);
			Consumer con = new Consumer(c);
			new Thread(p,"������A").start();
			new Thread(con,"������B").start();
			new Thread(p,"������C").start();
			new Thread(con,"������D").start();
	}
}

// ��Ա
class Clerk {
	private int product = 0;

	// ����
	public synchronized void get() {
		while (product >=1) {//Ϊ�˱�����ٻ�������,Ӧ������ʹ����ѭ����
			System.out.println("��Ʒ����");
			try {
				this.wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		} 
			System.out.println(Thread.currentThread().getName() + "����,ʣ��:" + ++product);
			this.notifyAll();
		
	}

	// ����
	public synchronized void sale() {
		while (product <= 0) {
			System.out.println("ȱ��");
			try {
				this.wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		} 
			System.out.println(Thread.currentThread().getName() + "����,ʣ��:" + --product);
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

// ������
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
