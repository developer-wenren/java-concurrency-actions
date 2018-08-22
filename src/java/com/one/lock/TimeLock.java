package com.one.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
- 限时锁

- Coding By One ON 08/22
*/
public class TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    public void run() {
        System.out.println(Thread.currentThread().getId()+"run");
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
                System.out.println(Thread.currentThread().getId()+"done");
            } else {
                System.out.println(Thread.currentThread().getId() + "get lock failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock r1 = new TimeLock();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        t1.start();
        t2.start(); // get lock failed
    }
}
