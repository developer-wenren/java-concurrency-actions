package com.one.lock;

import java.util.concurrent.locks.ReentrantLock;

/*
- 重入锁

- Coding By One ON 08/22
*/
public class ReentrantLockDemo implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();
    private static int i = 0;

    public void run() {
        for (int j = 0; j < 10000; j++) {
            // 重入锁允许反复进入,但必须提供相应次数的解锁
            lock.lock();
            lock.lock();
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
                lock.unlock();
                lock.unlock();
                // 多于加锁,会有异常 IllegalMonitorStateException
//                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ReentrantLockDemo());
        Thread t2 = new Thread(new ReentrantLockDemo());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(ReentrantLockDemo.i); // 20000
        System.out.println("done");
    }
}
