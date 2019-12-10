package juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Demo13_BlockingQueue {
    public static void main(String args[]) throws InterruptedException {
            /*
              当队列是空的,从队列中获取元素的操作会被阻塞,直到其他线程往队列中放入元素
              当队列是满的,向队列中添加元素的操作会被阻塞,直到其他线程从队列中拿走元素
             */

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        /*异常组
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        //java.lang.IllegalStateException: Queue full
        //System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //java.util.NoSuchElementException
        System.out.println(blockingQueue.remove());

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.element());    //查找队列首个元素
        */

        /*boolean组
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        //System.out.println(blockingQueue.offer("d"));   //false

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //System.out.println(blockingQueue.poll());     //null
        */

        /*阻塞组
        blockingQueue.put("a");
        blockingQueue.put("aa");
        blockingQueue.put("aaa");
        //blockingQueue.put("aaaa");        //没满则加,满则阻塞

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());       //没空则取,空则阻塞
            */

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        blockingQueue.offer("a",3L, TimeUnit.SECONDS);  //等你三秒
    }
}
