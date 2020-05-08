package juc;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Demo14_ForkJoin extends RecursiveTask<Long> {

    private static final long serialVersionUID = 13465648909L;
    private long start;
    private long end;
    private static final long THRESHOLD = 10000;

    public Demo14_ForkJoin(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String args[]) {
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new Demo14_ForkJoin(0, 10000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());

    }

    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long mid = (start + end) / 2;
            Demo14_ForkJoin left = new Demo14_ForkJoin(start, mid);
            left.fork();
            Demo14_ForkJoin right = new Demo14_ForkJoin(mid + 1, end);
            right.fork();
            return left.join() + right.join();
        }

    }
}
