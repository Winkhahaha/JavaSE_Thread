package synchronize;

public class De05_DeadLock {
    private static String s1 = "筷子左";
    private static String s2 = "筷子右";

    public static void main(String args[]) {
        /*
        为避免死锁,synchronize同步代码块不要嵌套
         */
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (s1) { //s1想拿s2,结果下面的线程先拿到了s2
                                        //下面的s2线程想拿s1,但是上面的线程因为还没有拿到s2无法释放s1,死锁
                        System.out.println(getName()+"获取" + s1 + " --> 等待" + s2);
                        synchronized (s2) {
                            System.out.println(getName()+"拿到" + s2 + "开吃");
                        }
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (s2) {
                        System.out.println(getName()+"获取" + s2 + " --> 等待" + s1);
                        synchronized (s1) {
                            System.out.println(getName()+"拿到" + s1 + "开吃");
                        }
                    }
                }
            }
        }.start();


    }
}
