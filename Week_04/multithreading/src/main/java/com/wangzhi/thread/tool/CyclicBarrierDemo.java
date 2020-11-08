package com.wangzhi.thread.tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(6, () -> {
            // 最后一个执行完的子线程执行回调函数
            System.out.println("回调>>"+Thread.currentThread().getName());
            System.out.println("回调>>线程组执行结束");
        });
        for (int i = 0; i < 5; i++) {
            new Thread(new readNum(i,cyclicBarrier)).start();
        }

        // 这个能做到主线程等待子线程结束再继续执行吗？
        // 百度两种方案，一种是使用线程池，一种是改变parties,使用第二种方案吧，感觉简单
        cyclicBarrier.await();
        System.out.println("==>各个子线程执行结束。。。。");
        System.out.println("==>主线程执行结束。。。。");

        //CyclicBarrier 可以重复利用，
        // 这个是CountDownLatch做不到的
//        for (int i = 11; i < 16; i++) {
//            new Thread(new readNum(i,cyclicBarrier)).start();
//        }
    }

    static class readNum implements Runnable {

        private Integer id;

        private CyclicBarrier cyclicBarrier;

        public readNum(Integer id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            synchronized (this) {

                System.out.println("id:"+id+","+Thread.currentThread().getName());
                try {
                    System.out.println("线程组任务"+id+"结束，其他任务继续");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
