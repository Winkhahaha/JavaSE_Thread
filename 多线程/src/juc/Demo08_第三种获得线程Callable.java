package juc;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/*
get方法一般放最后
 */
public class Demo08_第三种获得线程Callable {

    public static void main(String args[]) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyThread());
        new Thread(futureTask,"A").start(); //不管几个线程,只调用一次
        new Thread(futureTask,"B").start();
        System.out.println(Thread.currentThread().getName()+"完成");
        System.out.println(futureTask.get());
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call方法执行!");
        return 1024;
    }
}