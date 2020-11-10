package com.wangzhi.thread.future;

import java.util.Random;
import java.util.concurrent.*;

public class FutureTaskDemo {

    public static void main(String[] args) {
        // 方式1
        FutureTask<Integer> futureTask = new FutureTask<>(() -> new Random().nextInt());
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 方式2
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<Integer> task = new FutureTask<>(() -> new Random().nextInt());
        executor.submit(task);
        executor.shutdown();
        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
