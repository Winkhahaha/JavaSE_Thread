package createThread;

public class De03_impRunnable {
    public static void main(String args[]) {

        MyRunnable mr = new MyRunnable();   // 实例化一个*实现Runnable接口*的子类对象
        // Runnable target = mr 父类引用指向子类对象
        new Thread(mr).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("bb");
        }
    }
}
class MyRunnable implements Runnable{   // 定义一个类实现Runnable接口

    @Override               // 重写run方法
    public void run() {     // 将要执行的代码写在run方法中
        for (int i = 0; i < 1000; i++) {
            System.out.println("aaa");
        }
    }
}

