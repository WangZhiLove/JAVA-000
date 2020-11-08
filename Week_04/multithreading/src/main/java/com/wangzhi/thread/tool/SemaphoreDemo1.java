package com.wangzhi.thread.tool;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo1 {

    public static void main(String[] args) {
        int N = 8;

        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < N; i++) {
            new Worker(i, semaphore).start();
        }
    }
}

class Worker extends Thread{

    private int num;

    private Semaphore semaphore;

    public Worker(int n, Semaphore semaphore) {
        num = n;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquireUninterruptibly();
            System.out.println("工人" + this.num + "开始工作");
            Thread.sleep(2000);
            System.out.println("工人" + this.num + "工作完成，归还机器");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
