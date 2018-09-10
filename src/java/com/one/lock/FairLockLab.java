package com.one.lock;

import java.util.concurrent.locks.ReentrantLock;

/*
- 公平锁实验
- new ReentrantLock(true) 就是公平锁
- 公平排队,先进先出,避免及饥饿现象
- Coding By One ON 09/10
*/
public class FairLockLab {
    public static class FairLock implements Runnable {
        public static ReentrantLock fairLock = new ReentrantLock(true);

        public void run() {
            while (true) {
                try {
                    fairLock.lock();
                    System.out.println(Thread.currentThread().getName()+"get lock");
                    /**
                     * thread_r1get lock
                     * thread_r2get lock
                     * ...交替输出
                     */
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    fairLock.unlock();
                }
            }
        }

        public static void main(String[] args) {
            FairLock r1 = new FairLock();
            Thread t1= new Thread(r1,"thread_r1");
            Thread t2= new Thread(r1,"thread_r2");
            Thread t3= new Thread(r1,"thread_r3");
            t1.start();
            t2.start();
            t3.start();
        }
    }
}
