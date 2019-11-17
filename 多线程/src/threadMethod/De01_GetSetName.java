package threadMethod;

public class De01_GetSetName {
    public static void main(String args[]) {
        //constructForName();
        //setNameForName();
        // setName的第二种方式
       Thread t1= new Thread() {
            @Override
            public void run() {
                System.out.println(this.getName() + " -->线程开始");
            }
        };
       t1.setName("E");
       t1.start();
    }

    private static void setNameForName() {
        new Thread() {
            @Override
            public void run() {
                this.setName("D");
                System.out.println(this.getName() + " -->线程开始");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                this.setName("C");
                System.out.println(this.getName() + " -->线程开始");
            }
        }.start();
    }

    /*
    通过构造方法给线程setName
     */
    private static void constructForName() {
        new Thread("A") {
            @Override
            public void run() {
                System.out.println(this.getName() + " -->线程开始");
            }
        }.start();

        new Thread("B") {
            @Override
            public void run() {
                System.out.println(this.getName() + " -->线程开始");
            }
        }.start();
    }
}
