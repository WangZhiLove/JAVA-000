package com.wangzhi.thread.homework;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sum();
        });
        System.out.println("get before");
        System.out.println(integerCompletableFuture.get());
        System.out.println("get after");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        int first = 1;
        int right = 1;
        for (int i = 2; i <= a; i++) {
            int temp = right;
            right = first + right;
            first = temp;
        }
        return right;
    }
}
