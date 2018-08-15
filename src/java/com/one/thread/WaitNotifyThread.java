package com.one.thread;


/*
- 
- Coding By One ON 08/15
*/
public class WaitNotifyThread {
    final static Object object = new Object();

    public static class T1 extends Thread {
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T1 start!");
                try {
                    System.out.println(System.currentTimeMillis() + ":T1 wait");
                    object.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end");
            }
        }
    }

    public static class T2 extends Thread {
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T2 start!");
                try {
                    System.out.println(System.currentTimeMillis() + ":T2 notify");
                    object.notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 即使notify后,也要执行完 synchronized 内自身的代码,才能允许wait的其他线程唤醒
                System.out.println(System.currentTimeMillis() + ":T2 end");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {

                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();

    }
}
