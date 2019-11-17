package thread;

public class De02_NotifyAll {
    public static void main(String args[]) {
        final Printer p = new Printer();

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

class Printer {
    /*
     注意:在同步代码块中.用哪个对象锁,就用哪个对象调用wait方法
         为什么wait方法和notify方法定义在Object类中?
         --因为锁对象可以是任意对象,而Object是所有类的基类,所以定义在此处
         sleep方法和wait方法区别?
         --sleep方法必须传入参数(时间),到点自动醒来
         --wait方法可以传入参数(在参数的时间结束后等待),也可以不传入(直接等待)
         --sleep方法在同步函数或同步代码块中不释放锁(睡着了也抱着锁)
         --wait方法在同步函数或同步代码块中释放锁(必须释放,不然占着资源)
     */

    private int flag = 1;

    void print1() throws InterruptedException {
        synchronized (this) {
            while (flag != 1) {
                this.wait(); // 当前线程等待
            }
            System.out.print("你");
            System.out.print("好");
            System.out.print("呀");
            System.out.print("\r\n");
            flag = 2;
            this.notifyAll();   // 随机唤醒单个等待线程
        }
    }

    void print2() throws InterruptedException {
        synchronized (this) {
            while (flag != 2) {
                this.wait();
            }
            System.out.print("我");
            System.out.print("挺");
            System.out.print("好");
            System.out.print("\r\n");
            flag = 3;
            this.notifyAll();
        }
    }

    void print3() throws InterruptedException {
        synchronized (this) {
            while (flag != 3) {         // while循环是循环判断,每一次都会判断循环标记
                this.wait();
            }
            System.out.print("都");
            System.out.print("还");
            System.out.print("行");
            System.out.print("\r\n");
            flag = 1;
            this.notifyAll();
        }
    }
}