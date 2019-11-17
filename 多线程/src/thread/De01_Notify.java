package thread;


public class De01_Notify {
    /*
    等待唤醒机制
     */
    public static void main(String args[]){
        final Printer2 p = new Printer2();

        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        p.print1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        p.print2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }
}

class Printer2 {

    private int flag = 1;

   void print1() throws InterruptedException {
       synchronized (this) {
           if(flag != 1){
               this.wait(); // 当前线程等待
           }
           System.out.print("你");
           System.out.print("好");
           System.out.print("呀");
           System.out.print("\r\n");
           flag = 2;
           this.notify();   // 随机唤醒单个等待线程
       }
    }

         void print2() throws InterruptedException {
        synchronized (this) {
            if(flag!=2){
                this.wait();
            }
            System.out.print("我");
            System.out.print("挺");
            System.out.print("好");
            System.out.print("\r\n");
            flag =1;
            this.notify();
        }
    }
}
