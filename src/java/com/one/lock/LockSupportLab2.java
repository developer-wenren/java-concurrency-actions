package com.one.lock;

import java.util.concurrent.locks.LockSupport;

/*
 * 线程阻塞工具类
 *
 * Coding By One ON 09/11
 */
public class LockSupportLab2 {
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
            if (Thread.interrupted()) {
                System.out.println(getName() + "被中断了");
            }
            System.out.println("in " + getName() + "执行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        // 主动中断
        t1.interrupt();
        LockSupport.unpark(t2);
        /**
         * in t1
         * in t2
         * t1被中断了
         * in t1执行结束
         * in t2执行结束
         */
    }
}
