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
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new CallableDemo());
        new Thread(integerFutureTask).start();
        Integer integer = integerFutureTask.get();
        System.out.println(integer);
    }
}
