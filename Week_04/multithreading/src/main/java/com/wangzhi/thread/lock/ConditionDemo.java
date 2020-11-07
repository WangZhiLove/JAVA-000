package com.wangzhi.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[20];

    int count = 0, putpty = 0, takeptr = 0;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            // 对象满了，通知不空线程去取
            while (count == items.length) {
                notFull.await();
            }
            items[putpty] = x;
            if (++ putpty == items.length) {
                putpty = 0;
            }
            ++ count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            if (count == 0) {
                notEmpty.await();
            }
            Object obj = items[takeptr];
            if (++ takeptr == items.length) {
                takeptr = 0;
            }
            -- count;
            notFull.signal();
            return obj;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo conditionDemo = new ConditionDemo();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                Object object = new Object();
                try {
                    conditionDemo.put(object);
                    System.out.println("放线程：" + conditionDemo.count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                Object object = new Object();
                try {
                    conditionDemo.take();
                    System.out.println("读线程：" + conditionDemo.count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
