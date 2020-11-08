package com.wangzhi.thread.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 主线程等待的案例
 */
public class SemaphoreDemo2 {

    private final static int threadCount = 20;

    private static void test(int threadNum) throws Exception {
        System.out.println("id:"+threadNum+","+Thread.currentThread().getName());
        Thread.sleep(1000);
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            service.execute(() -> {
                try {
                    // 一次锁住3个资源，也就是当前变成了串行执行
                    semaphore.acquire(1);
                    test(threadNum);
                    semaphore.release(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }
        // 这里如果不关闭，主线成可能永远不会结束或者等待的时间很久
        service.shutdown();
    }

}
