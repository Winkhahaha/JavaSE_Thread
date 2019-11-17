package threadMethod;

public class De06_yield {
    /*
    礼让线程:yield让出cpu
     */
    public static void main(String args[]){
        new MyThread().start();
        new MyThread().start();
    }

}
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            if(i % 10 == 0){
                Thread.yield();     //让出cpu,让另一条线程执行
            }
            System.out.println(getName()+ " -->"+i);
        }
    }
}
