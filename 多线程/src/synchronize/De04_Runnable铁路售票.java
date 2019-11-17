package synchronize;

public class De04_Runnable铁路售票 {

    /*
    铁路售票,一共100张,通过四个窗口卖完
     */
    public static void main(String args[]) {

        MyTicket mt = new MyTicket();
        // 不用加静态,因为只创建了一次对象
        new Thread(mt).start();
        new Thread(mt).start();
        new Thread(mt).start();
        new Thread(mt).start();

//        Thread t1 = new Thread(mt);  多次启动一个线程是非法的
//        t1.start();
//        t1.start();
//        t1.start();
//        t1.start();
    }
}
class MyTicket implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        while (true) {
            synchronized (Ticket.class) {
                if (ticket <= 0) {
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " -->这是第" + ticket-- + "张票");
            }
        }
    }
}

