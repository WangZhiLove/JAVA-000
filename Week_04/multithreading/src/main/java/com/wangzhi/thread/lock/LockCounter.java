package com.wangzhi.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 锁的示例
 */
public class LockCounter {
    private int sum = 0;
    /**
     * 可重入锁 + 公平锁
     * 可重入锁的概念是：第二次进入同一个锁的时候是否阻塞，会不会产生死锁
     * 公平锁的理解：公平指的是根据进入的顺序来说，越早进入被执行的机会越大；非公平就是大家的机会都一样
     */
    private Lock lock = new ReentrantLock(true);

    public int addAndGet() {
        try {
            lock.lock();
            return ++ sum;
        } finally {
            lock.unlock();
        }
    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) {
        int loop_sum = 100_0000;
        final LockCounter lockCounter = new LockCounter();
        // 很明显，这里没有产生死锁
        IntStream.range(0, loop_sum).parallel().forEach(i -> lockCounter.addAndGet());
        System.out.println(lockCounter.getSum());
    }

}
