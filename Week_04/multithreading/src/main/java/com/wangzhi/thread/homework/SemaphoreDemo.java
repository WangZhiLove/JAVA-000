package com.wangzhi.thread.homework;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        int first = 1;
        int right = 1;
        for (int i = 2; i <= a; i++) {
            int temp = right;
            right = first + right;
            first = temp;
        }
        return right;
    }
}
