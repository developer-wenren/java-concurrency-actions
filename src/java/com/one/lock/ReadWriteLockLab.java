package com.one.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * 读写锁实验
 * 减少锁竞争
 * 读读不互斥,不阻塞
 * 读写互斥
 * 写写阻塞
 * 应对读操作远大于写操作的多线程处理
 * Coding By One ON 09/10
 */
public class ReadWriteLockLab {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handRead(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public Object handWrite(Lock lock, int index) {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) {
        final ReadWriteLockLab lab = new ReadWriteLockLab();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    lab.handRead(readLock);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    lab.handWrite(writeLock, new Random().nextInt());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i =0; i<18;i++) {
            new Thread(readRunnable).start();
        }

        for (int i =18; i<20;i++) {
            new Thread(writeRunnable).start();
        }
    }

}
