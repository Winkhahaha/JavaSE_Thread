package createThread;

import threadGroup.MyRunnable;

import java.util.concurrent.*;

public class De05_Callable {

    /*
        Callable创建
     */

    public static void main(String args[]) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<Integer> f1 = pool.submit(new MyCallable(100));      // 将线程放进池子里并执行
        Future<Integer> f2 = pool.submit(new MyCallable(50));

        //获取call()结果
        System.out.println(f1.get());
        System.out.println(f2.get());
    }
}

class MyCallable implements Callable<Integer> {
    private int num;

    public MyCallable(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= num; i++) {
            sum += i;
        }
        return sum;
    }
}
