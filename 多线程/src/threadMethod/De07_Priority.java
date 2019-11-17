package threadMethod;

public class De07_Priority {
    /*
    设置优先级
     */
    public static void main(String args[]){
            Thread t1 = new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        System.out.println(getName()+ " -->aaa优先级大");
                    }
                }
            };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println(getName()+ " -->bbb小");
                }
            }
        };

        //t1.setPriority(10);     // 设置优先级
        //t2.setPriority(1);
        t1.setPriority(Thread.MAX_PRIORITY);    // //设置最大优先级
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();

    }
}
