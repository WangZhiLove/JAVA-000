package com.wangzhi.thread.homework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        Future<Integer> submit = fixedThreadPool.submit(() -> {
            Thread.sleep(2000);
            return sum();
        });
        fixedThreadPool.shutdown();
        System.out.println("get before");
        System.out.println(submit.get());
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
