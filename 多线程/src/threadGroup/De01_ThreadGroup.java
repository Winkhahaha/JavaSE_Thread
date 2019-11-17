package threadGroup;

public class De01_ThreadGroup {
    /*
    线程组
     */
    public static void main(String args[]) {

        //Demo1();
        ThreadGroup tg = new ThreadGroup("我是一个新的线程组");  // 创建新的线程组
        MyRunnable mr = new MyRunnable();

        Thread t1 = new Thread(tg,mr,"战三");     // 将线程t1,t2放在组里
        Thread t2 = new Thread(tg,mr,"李四");

        System.out.println(t1.getThreadGroup().getName());  // 获取线程所在组名
        System.out.println(t2.getThreadGroup().getName());

         // tg.setDaemon(true);     // 成组设置线程状态
    }

    private static void Demo1() {
        MyRunnable mr = new MyRunnable();
        Thread t1 = new Thread(mr, "张三");
        Thread t2 = new Thread(mr, "李四");

        ThreadGroup group1 = t1.getThreadGroup();
        ThreadGroup group2 = t2.getThreadGroup();

        System.out.println(group1.getName());   // 默认是主线程
        System.out.println(group2.getName());
    }
}

