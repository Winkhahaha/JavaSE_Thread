package juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
  多个线程同事读一个共享资源类没有问题,为了满足并发量,读取资源应该可以同时进行
  如果一个线程想去写共享资源,就不应该有其他线程可以对该资源进行读或写
 */
public class Demo12_ReadWriteLock {
    public static void main(String args[]) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            int temp = i;
            new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myCache.put(temp + "", temp + "");
            }, i + "号写线程").start();
        }

        for (int i = 0; i < 5; i++) {
            int temp = i;
            new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myCache.get(temp + "");
            }, i + "号读线程").start();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        try {
            readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "-->写入数据...");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "-->写入成功");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        try {
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "-->读取数据...");
            Object object = map.get(key);
            System.out.println(Thread.currentThread().getName() + "-->读取成功:" + object);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
