package juc.线程池;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Demo05_异步回调 {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ":没有返回");
        });
        voidCompletableFuture.get();

        // 异步回调
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ":有返回");
            return 1024;
        });

        //   BiConsumer<? super T, ? super Throwable>
        integerCompletableFuture.whenComplete((t, u) -> {      //正常则返回
            System.out.println(t + "--------" + u.getMessage());
        }).exceptionally(f -> {               // 如果有异常
            System.out.println("Exception:" + f.getMessage());
            return 404;
        });

    }
}
