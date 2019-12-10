package juc;

import java.util.concurrent.CountDownLatch;

/*
  计数走人,最后关门
 */
public class Demo09_CountDownLatch {
    public static void main(String args[]) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "-->关门前离开");
                countDownLatch.countDown(); //走一个减少一个
            }, i + "").start();
        }

        countDownLatch.await();     //计数没为0之前就等着
        System.out.println(Thread.currentThread().getName() + "关门");
        //closeDoor();
    }

    private static void closeDoor() {
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "-->关门前离开");
            }, i + "").start();
        }

        System.out.println(Thread.currentThread().getName() + "关门");
    }
}
