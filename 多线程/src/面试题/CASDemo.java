package 面试题;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String args[]) {
        // 当前线程将5放入主存
        AtomicInteger atomicInteger = new AtomicInteger(5);
        // 如果主存中还是5(期待没被修改,仍是5),则将2019刷入主存进行更新
        // i++;
        // atomicInteger.getAndIncrement();
        // ++i
        // atomicInteger.incrementAndGet();
        System.out.println(atomicInteger.compareAndSet(5, 2019) + " 当前值:" + atomicInteger.get());
    }
}
