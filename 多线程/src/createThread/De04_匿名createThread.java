package createThread;

public class De04_匿名createThread {
    public static void main(String args[]){

            new Thread(){       // 继承Thread类
                @Override       // 重写run方法
                public void run() { // 补充方法体
                    for (int i = 0; i < 1000; i++) {
                        System.out.println("aaaaaaaaa");
                    }
                }
            }.start();      // 开启线程

        // 匿名Runnable
        new Thread(new Runnable(){      // 将Runnable的子类对象传递给Thread的构造中
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("bbbbbb");
                }
            }
        }).start();

    }
}
