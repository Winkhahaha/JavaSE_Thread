package juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class Demo07_集合不安全 {
    public static void main(String args[]) {

         /*
         java.util.ConcurrentModificationException:并发修改异常
         解决方案:
         1.Vector,底层是同步(syn)方法
         2.Collections.synchronizedList(new ArrayList<>())转换为线程安全的集合类
         3.CopyOnWriteArrayList<>()写时复制
         */


        Map<String, String> map = new ConcurrentHashMap();//Collections.synchronizedMap(new HashMap<>())//new HashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8)); // 写
                System.out.println(map);   // 读
            }, String.valueOf(i)).start();
        }
        //SetNotSafe();
        //listNotSafe();
    }

    private static void SetNotSafe() {
        Set<String> set = new CopyOnWriteArraySet();//Collections.synchronizedSet(new HashSet<>());//new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8)); // 写
                System.out.println(set);   // 读
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {

        List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());//new Vector<>();//new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8)); // 写
                System.out.println(list);   // 读
            }, String.valueOf(i)).start();
        }
    }
}
