package threadMethod;

public class De04_Daemon {
    /*
    守护线程
     */
    public static void main(String args[]){
       Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {

                    System.out.println(getName() + " -->aaa");
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {

                    System.out.println(getName() + " -->我是守护线程");
                }
            }
        };

        t2.setDaemon(true);     // 设置为守护线程
        t1.start();
        t2.start();
    }
}
