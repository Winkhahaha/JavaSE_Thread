package juc;


/*
  高内聚低耦合的前提下,线程        操作(对外暴露的调用方法)           资源类(Ticket)
 */
public class Demo01_售票 {
    public static void main(String args[]) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i <= 400; i++) {
                ticket.saleTicket();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i <= 400; i++) {
                ticket.saleTicket();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i <= 400; i++) {
                ticket.saleTicket();
            }
        }, "C").start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i <= 400; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "A").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i <= 400; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "B").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i <= 400; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "C").start();
    }
}

// 资源类
class Ticket {
    private static int number = 300;

    // 操作
    public synchronized void saleTicket() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "-->卖出第:" + number-- + "张票,还剩下:" + number + "张");
        }
    }
}
