package com.wangzhi.thread.pool.create;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateTheadPool {
    public static void main(String[] args) {
        // 使用工具类创建线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        // 固定大小线程池创建建议：如果是CPU密集型：线程池的大小设置为N或N+1；如果是I/O密集型，线程池的大小设置为 N * 2 或者 N * 2 + 2
        // CPU密集型指的是 加减乘除和频繁的创建对象，访问对象的属性和方法
        // I/O密集型指的是 磁盘文件读写和网络等
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());


        // 手动创建线程池
        int coreSize = Runtime.getRuntime().availableProcessors();
        int maxSize = Runtime.getRuntime().availableProcessors() * 2;
        BlockingQueue<Runnable> linkedBlockingDeque = new LinkedBlockingDeque<Runnable>(500);
        new ThreadPoolExecutor(coreSize, maxSize, 1, TimeUnit.MINUTES, linkedBlockingDeque, new ThreadFactory() {
            private AtomicInteger serial = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                // thread.setDaemon(true);
                thread.setName("手动线程池：" + serial.getAndIncrement());
                return thread;
            }
        });
    }
}
