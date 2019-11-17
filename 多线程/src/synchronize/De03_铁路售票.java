package synchronize;

public class De03_铁路售票 {

    /*
    铁路售票,一共100张,通过四个窗口卖完
     */
    public static void main(String args[]) {
        new Ticket().start();
        new Ticket().start();
        new Ticket().start();
        new Ticket().start();
    }
}

class Ticket extends Thread {
    private static int ticket = 100;
    //private static Object obj = new Object(); 如果用引用类型的成员变量当做锁对象,必须是静态的,不然锁各不相同
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
                System.out.println(getName() + " -->这是第" + ticket-- + "张票");
            }
        }
    }
}
