package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
    指定数量的线程都执行完再后执行方法
 */
public class Demo10_CyclicBarrier {
    public static void main(String args[]) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙");
        });

        for (int i = 1; i <= 7; i++) {
            int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "-->" + temp + "龙珠");
                try {
                    cyclicBarrier.await();      //等他们都执行完后,执行一个+1,到7执行召唤
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, i + "线程").start();
        }
    }
}
