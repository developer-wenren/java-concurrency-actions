package com.one.lock;

import java.util.concurrent.locks.LockSupport;

/*
 * 线程阻塞工具类
 * 可以支持中断,且不抛异常,直接返回.
 * Coding By One ON 09/11
 */
public class LockSupportLab {
    private static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String t2) {
            super();
            this.setName(t2);
        }

        @Override
        public void run() {
            System.out.println("in " + getName());
            // 阻塞线程
            LockSupport.park();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        // 取消阻塞
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
