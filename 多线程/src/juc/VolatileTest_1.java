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
	 * volatile:������̲߳�����������ʱ,���Ա����ڴ��е������ǿɼ���,��ʱˢ�µ�
	 * �����synchronize��һ�ֽ���������ͬ������
	 * ע��:volatile���߱�������
	 * volatile���ܱ�֤������ԭ����
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
