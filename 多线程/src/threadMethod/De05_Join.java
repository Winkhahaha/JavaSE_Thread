package threadMethod;

public class De05_Join {
    /*
    join线程,当前线程暂停,等待指定的线程执行结束之后,当前线程再继续
     */
    public static void main(String args[]) {


        final Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(getName() + " -->a要插队");
                }
            }
        };
        //匿名内部类在使用他所在方法(main)的局部变量时,必须其用final修饰
        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if (i == 2) {
                        try {
                            // t1.join();
                            t1.join(1); // a插队1毫秒,过了指定时间之后两条线程交替执行
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(getName() + " -->bbb");
                }
            }
        };
        t1.start();
        t2.start();
    }
}
