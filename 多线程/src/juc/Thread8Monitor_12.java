package juc;

/*
 * 判断打印的one or two
 * 1.两个普通同步方法,两个线程,标准打印//one,two
 * 2.新增sleep方法给getOne,打印?one two
 * 3.新增getThree,打印?/two,three,one
 * 4.两个普通同步方法,两个number对象,标准打印//two,one
 * 5.修改getone为静态同步,打印two,one
 * 6.修改两个方法为静态同步方法,one,two
 * 7.一个是静态同步,一个非静态同步,两个number对象?two,one
 * 8.两个都是静态同步方法,两个number对象?one two
 * 
 * 线程八锁的关键:
 * 1.非静态方法的锁默认为this,静态方法对应的锁是Number.class实例
 * 2.某一个时刻内,只能有一个线程持有锁,无论几个方法
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
