package juc;

/*
 * �жϴ�ӡ��one or two
 * 1.������ͨͬ������,�����߳�,��׼��ӡ//one,two
 * 2.����sleep������getOne,��ӡ?one two
 * 3.����getThree,��ӡ?/two,three,one
 * 4.������ͨͬ������,����number����,��׼��ӡ//two,one
 * 5.�޸�getoneΪ��̬ͬ��,��ӡtwo,one
 * 6.�޸���������Ϊ��̬ͬ������,one,two
 * 7.һ���Ǿ�̬ͬ��,һ���Ǿ�̬ͬ��,����number����?two,one
 * 8.�������Ǿ�̬ͬ������,����number����?one two
 * 
 * �̰߳����Ĺؼ�:
 * 1.�Ǿ�̬��������Ĭ��Ϊthis,��̬������Ӧ������Number.classʵ��
 * 2.ĳһ��ʱ����,ֻ����һ���̳߳�����,���ۼ�������
 */
public class Thread8Monitor_12 {
	public static void main(String[] args) {
		Number n = new Number();
		Number n2 = new Number();
		new Thread(new Runnable() {

			@Override
			public void run() {
				n.getOne();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				n2.getTwo();
			}
		}).start();
		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				n.getThree();
//			}
//		}).start();
	}

}

class Number {
	public static synchronized void getOne() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println("one");
	}

	public static synchronized void getTwo() {
		System.out.println("two");
	}
	
	public void getThree() {
		System.out.println("three");
	}
}
