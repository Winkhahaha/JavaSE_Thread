package threadMethod;

public class De02_currentThread {
    public static void main(String args[]) {
        new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + " -->线程开始");
            }
        }.start();

        new Thread(new Runnable() {      // 将Runnable的子类对象传递给Thread的构造中
            @Override
            public void run() {
                // Thread.currentThread().getName()获取当前正在执行线程的名字
                System.out.println(Thread.currentThread().getName() + " -->线程开始");
            }
        }).start();

        // 获取主线程名称(或者设置名字)
        Thread.currentThread().setName("我是main线程");
        System.out.println(Thread.currentThread().getName());

    }
}
