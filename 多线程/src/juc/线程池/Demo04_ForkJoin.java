package juc.线程池;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Demo04_ForkJoin {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        /*
          分支合并
          ForkJoinPool
          ForkJoinTask
          RecursiveTask
         */
        MyTask myTask = new MyTask(0,100);

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = pool.submit(myTask);
        System.out.println(submit.get());
        pool.shutdown();
    }
}

class MyTask extends RecursiveTask<Integer> {

    private static final Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - begin <= ADJUST_VALUE) {
            for (int i = begin; i <=end; i++) {
                result=result+i;
            }
        }else{
            int middle = (end+begin)/2;
            MyTask task1 = new MyTask(begin,middle);    //从中间分,算左边
            MyTask task2 = new MyTask(middle+1,end);    //算右边
            task1.fork();
            task2.fork();
            result = task1.join()+task2.join();
        }
        return result;
    }
}
