package createThread;

public class De01_Thread {
    /*
     证明jvm是多线程
    */
    public static void main(String args[]) {
        for (int i = 0; i < 100000; i++) {
            new Demo();
        }

        for (int i = 0; i < 10000; i++) {
            System.out.println("我是main线程的执行代码");
        }

    }
}

class Demo {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("垃圾被清扫了");
    }
}
