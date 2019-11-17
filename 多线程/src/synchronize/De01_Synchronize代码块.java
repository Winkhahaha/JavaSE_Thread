package synchronize;

import java.util.Date;

public class De01_Synchronize代码块 {
    /*
    同步代码块:
    希望某一段代码执行的过程中CPU不要切换到其他线程工作. 这时就需要同步
     */
    public static void main(String args[]) {
        final Printer p = new Printer();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    p.print1(); // 匿名内部类使用局部变量需要final修饰
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    p.print2();
                }
            }
        }.start();
    }
}

class Printer {
    Demo d = new Demo();
    void print1() {
        // synchronized (new Demo()){            // //锁对象可以是任意对象,但是被锁的代码需要保证是同一把锁,不能用匿名对象
        synchronized (d){
            System.out.print("你");
            System.out.print("好");
            System.out.print("呀");
            System.out.print("\r\n");
        }
    }

    void print2() {
        // synchronized (new Demo()){          // 锁对象不能用匿名对象,匿名对象不是同一个对象
        synchronized (d){
        System.out.print("我");
            System.out.print("挺");
            System.out.print("好");
            System.out.print("\r\n");
        }
    }
}
class Demo{

}
