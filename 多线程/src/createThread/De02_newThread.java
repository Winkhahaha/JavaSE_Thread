package createThread;

public class De02_newThread {
    public static void main(String args[]) {
        MyThread mt = new MyThread();   // 创建Thread的子类对象
        //mt.run();                       // 没有开启线程,只是很平常的调用方法
        mt.start();                     // 开启线程

        for (int i = 0; i < 1000; i++) {
            System.out.println("bb");
        }
    }

}

class MyThread extends Thread {  // 继承Thread类
    @Override                   // 重写run方法
    public void run() {         // 将要执行的代码写在run方法中
        for (int i = 0; i < 1000; i++) {
            System.out.println("aaa");
        }
    }
}
