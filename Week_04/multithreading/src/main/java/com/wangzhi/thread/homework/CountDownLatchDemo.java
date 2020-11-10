package com.wangzhi.thread.homework;

import java.util.concurrent.*;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        Future<Integer> submit = cachedThreadPool.submit(() -> {
            Thread.sleep(5000);
            countDownLatch.countDown();
            return sum();
        });
        cachedThreadPool.shutdown();
        System.out.println("await before=====");
        // 仅仅是起到让祝线程等待的作用
        countDownLatch.await();
        System.out.println("await after=====");
        System.out.println(submit.get());
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
