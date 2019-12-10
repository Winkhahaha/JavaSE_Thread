package juc;

import java.util.concurrent.TimeUnit;

public class Demo06_八锁 {
    public static void main(String args[]) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                //phone.send1();
                Phone.send11();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(200);

        new Thread(() -> {
            phone.send2();
            //phone2.send2();
        }).start();

        new Thread(() -> {
            phone.hello();
        }).start();


    }
}

/*
 1.标准访问,先打印1还是2?
 2.send1暂停三秒,先打印哪个?
 3.新增一个普通方法(无syn)hello,先打印它和send1哪个?
 4.两个实例化对象,先send1还是send2?
 5.两个静态同步方法,同手机,先打印哪个?
 6.两个静态同步方法,两手机,先打印哪个?
 7.一个是静态同步,一个非静态同步,两个对象?
 8.两个都是静态同步方法,两个对象?

 线程八锁的关键:
 * 1.非静态同步方法的锁
 * 默认为this,静态同步方法对应的锁是Phone.class实例
 * 5.一旦一个静态同步方法获取锁之后,其他的静态同步方法都必须等待该方法释放锁之后才能获取锁(不考虑是不是同一个实例对象,因为是同一个Phone.class)
 * 2.某一个时刻内,只能有一个同步方法持有锁,其他同步方法必须等它释放才能获取,普通同步方法持有的锁是对象实例化的this
 * 3.普通方法不受同步锁影响
 * 4.多实例对象(不是同一把锁)相当于分灶吃饭,各吃各的互不影响
 */
class Phone {
    public synchronized void send1() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("send1");
    }

    public synchronized void send2() {
        System.out.println("send2");
    }

    public void hello(){
        System.out.println("hello");
    }

    public static synchronized void send11() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("send11静态同步方法");
    }

    public static synchronized void send22() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("send22静态同步方法");
    }
}
