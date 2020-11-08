package com.wangzhi.thread.tool;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new readNum(i, countDownLatch)).start();
        }
        // 如果不等待，则会出现主线程先结束
        countDownLatch.await();
        System.out.println("==>各个子线程执行结束。。。。");
        System.out.println("==>主线程执行结束。。。。");
    }

    static class readNum implements Runnable {

        private Integer id;

        private CountDownLatch countDownLatch;

        public readNum(Integer id, CountDownLatch countDownLatch) {
            this.id = id;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            synchronized (this) {
                System.out.println("id:"+id+","+Thread.currentThread().getName());
                System.out.println("线程组任务"+id+"结束，其他任务继续");
                countDownLatch.countDown();
            }

        }
    }
}
