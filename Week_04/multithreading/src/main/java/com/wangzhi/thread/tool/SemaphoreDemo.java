package com.wangzhi.thread.tool;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class SemaphoreDemo {

    private int sum = 0;

    private Semaphore readSemaphore = new Semaphore(100, false);

    private Semaphore writeSemaphore = new Semaphore(1);

    public int incrAndGet() throws InterruptedException {
        try {
            writeSemaphore.acquireUninterruptibly();
            /// 由于写只设置了1个并发线程数，相当于独占锁，所以这里加上就是每五秒执行一次
            // Thread.sleep(5000);
            return ++ sum;
        } finally {
            writeSemaphore.release();
        }
    }

    public int getSum() throws InterruptedException {
        try {
            // readSemaphore.acquireUninterruptibly();
            readSemaphore.acquire();
            /// 一次输出100个
            Thread.sleep(5000);
            return sum;
        } finally {
            readSemaphore.release();
        }
    }

    public static void main(String[] args) {
        int loop = 1000;
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        for (int i = 0; i < loop; i++) {
            new Thread(() -> {
                try {
                    System.out.println(semaphoreDemo.incrAndGet());
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < loop; i++) {
            new Thread(() -> {
                try {
                    System.out.println(semaphoreDemo.getSum());
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }).start();
        }

    }
}
