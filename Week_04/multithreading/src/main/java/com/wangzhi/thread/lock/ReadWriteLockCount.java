package com.wangzhi.thread.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

public class ReadWriteLockCount {

    private int sum = 0;

    /**
     * 可重入读写公平锁
     * ReadWriteLock管理一组锁，一个读锁，一个写锁。 读锁可以在没有写锁的时候被多个线程同时持有，写锁是独占的。
     * 所有读写锁的实现必须确保写操作对读操作的内存影响。每次只能有一个写线程，但是同时可以有多个线程并发地读数据。ReadWriteLock适用于读多写少的并发情况。
     */
    private ReadWriteLock lock = new ReentrantReadWriteLock(true);

    public int incrAndGet() {
        try {
            lock.writeLock().lock();
            return ++ sum;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getSum() {
        try {
            lock.readLock().lock();
            // 我电脑6核，这里默认同时可以有12个线程同时持有读锁，之后的线程就需要排队等待了，默认是2倍CPU核数的线程并行
            Thread.sleep(5000);
            return sum;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
        return sum;
    }

    public static void main(String[] args) {
        // int loop_sum = 100_0000;
        int loop_sum = 1_0000;
        final ReadWriteLockCount readWriteLockCount = new ReadWriteLockCount();
        // 很明显，这里没有产生死锁
        IntStream.range(0, loop_sum).parallel().forEach(i -> readWriteLockCount.incrAndGet());
        IntStream.range(0, loop_sum).parallel().forEach(i -> System.out.println(readWriteLockCount.getSum()));
    }
}
