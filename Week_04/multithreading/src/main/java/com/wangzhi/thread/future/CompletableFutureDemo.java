package com.wangzhi.thread.future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        // 1. 变换结果，后续操作利用前一个的结果   Hello love
        String join = CompletableFuture.supplyAsync(() -> "Hello").thenApplyAsync(v -> v + " love").join();
        System.out.println(join);
        // 2. 消费  2. 消费=======：Hello
        CompletableFuture.supplyAsync(() -> "Hello ").thenAccept(v -> {
            System.out.print("2. 消费======: ");
            System.out.println(v);
        });
        // 3. 组合 hello love
        String combine = CompletableFuture.supplyAsync(() -> "Hello").thenCombine(CompletableFuture.supplyAsync(() -> " love"), (s1, s2) -> s1 + s2).join();
        System.out.println(combine);
        // henCompose:hello, java course.
        CompletableFuture.supplyAsync(() -> "Hello, java course.")
                .thenApply(String::toUpperCase).thenCompose(s -> CompletableFuture.supplyAsync(s::toLowerCase)).thenAccept(v -> { System.out.println("thenCompose:"+v);});
        // 4. 竞争
        String either = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "王智";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "您";
        }), s -> s).join();
        // 您
        System.out.println(either);
        // 5. 补偿异常
        System.out.println("=====>5.补偿异常");
        String exception = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(true) {
                throw new RuntimeException("exception test!");
            }

            return "Hi Boy";
        }).exceptionally(e->{
            System.out.println(e.getMessage());
            return "Hello wangzhi";
        }).join();
        // Hello wangzhi
        System.out.println(exception);
    }
}
