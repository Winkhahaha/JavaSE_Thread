package 面试题;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String args[]) {
//        System.out.println("模拟ABA问题:");
//        new Thread(() -> {
//            // ABA
//            atomicReference.compareAndSet(100, 101);
//            atomicReference.compareAndSet(101, 100);
//        }, "T1").start();
//
//        new Thread(() -> {
//            try {
//                // 暂停一秒钟,保证T1线程干完所有事情
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(atomicReference.compareAndSet(100, 2019) + " 当前" + atomicReference.get());
//        }, "T2").start();
//
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("解决ABA问题:");

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 第一次版本号:" + stamp);
            try {
                // 暂停一秒钟T3,等待T4拿到相同的版本号
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // ABA
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 第二次版本号:" + atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 第三次版本号:" + atomicStampedReference.getStamp());
        }, "T3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 第一次版本号:" + stamp);
            try {
                // 暂停三秒钟T4
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println("当期T4的版本号:" + stamp);
            System.out.println("当期主存的真实版本号:" + atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + "修改成功否:" + result + " 当前值:" + atomicStampedReference.getReference());
        }, "T4").start();
    }
}
