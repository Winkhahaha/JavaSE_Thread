package threadMethod;

public class De03_Sleep {
    public static void main(String args[]) throws InterruptedException {

        //mainSleep();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + " -->aaa");
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + " -->bbb");
                }
            }
        }.start();
    }

    private static void mainSleep() throws InterruptedException {
        for (int i = 20; i >= 0; i--) {
            Thread.sleep(1000);
            System.out.println("倒计时:" + i + "秒");
        }
    }
}
