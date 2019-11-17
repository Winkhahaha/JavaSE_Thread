package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class De03_ReentrantLock {

    public static void main(String args[]) {
        Printer3 p = new Printer3();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        p.print1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        p.print2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        p.print3();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}

class Printer3 {
    private int flag = 1;
    private ReentrantLock r = new ReentrantLock();
    private Condition c1 = r.newCondition();
    private Condition c2 = r.newCondition();
    private Condition c3 = r.newCondition();

    void print1() throws InterruptedException {
        r.lock();
        if(flag != 1) {
            c1.await();
        }
        System.out.print("你");
        System.out.print("好");
        System.out.print("呀");
        System.out.print("\r\n");
        flag = 2;
        c2.signal();    // 唤醒指定线程
        r.unlock();
    }


    void print2() throws InterruptedException {

        r.lock();
        if (flag != 2) {
            c2.await();
        }
        System.out.print("我");
        System.out.print("挺");
        System.out.print("好");
        System.out.print("\r\n");
        flag = 3;
        c3.signal();
        r.unlock();

    }

    void print3() throws InterruptedException {
        r.lock();
        if (flag != 3) {         // while循环是循环判断,每一次都会判断循环标记
           // this.wait();
            c3.await();
        }
        System.out.print("都");
        System.out.print("还");
        System.out.print("行");
        System.out.print("\r\n");
        flag = 1;
      //  this.notifyAll();
        c1.signal();
        r.unlock();
    }

}