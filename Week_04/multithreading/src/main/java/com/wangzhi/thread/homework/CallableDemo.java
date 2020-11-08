package com.wangzhi.thread.homework;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable + FutureTask 实现获取线程的返回值
 */
public class CallableDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(2000);
        return sum();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new CallableDemo());
        new Thread(integerFutureTask).start();
        System.out.println("主线程继续执行");
        Integer integer = integerFutureTask.get();
        System.out.println("异步获取到的结果：" + integer);
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


