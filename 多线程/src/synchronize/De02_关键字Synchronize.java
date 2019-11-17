package synchronize;

public class De02_关键字Synchronize {
    /*
    同步代码块:
    希望某一段代码执行的过程中CPU不要切换到其他线程工作. 这时就需要同步
     */
    public static void main(String args[]) {
        final Printer2 p = new Printer2();

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

class Printer2 {
    Demo d = new Demo();

    // 声明一个同步方法
    // 非静态的同步方法锁对象是什么? 锁对象是this
    // // 静态的同步方法锁对象是什么? 锁对象是字节码文件.class

    public static synchronized void print1() {
        System.out.print("你");
        System.out.print("好");
        System.out.print("呀");
        System.out.print("\r\n");

    }

    public static void print2() {
        // synchronized (new Demo()){          // 锁对象不能用匿名对象,匿名对象不是同一个对象
        synchronized (Printer2.class) {
            System.out.print("我");
            System.out.print("挺");
            System.out.print("好");
            System.out.print("\r\n");
        }
    }
}


