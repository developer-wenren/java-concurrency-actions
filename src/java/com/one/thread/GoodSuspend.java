package com.one.thread;

/*
- 推荐方式挂起线程

- Coding By One ON 08/15
*/
public class GoodSuspend {
    public static Object u = new Object();

    private static class ChangeObjectThread extends Thread {
        volatile boolean suspendme = false;

        public void setSuspendme() {
            suspendme = true;
        }

        public void resumeMe() {
            suspendme = false;
            synchronized (this) {
                notify();
            }
        }

        public ChangeObjectThread() {
            super();
        }

        public void run() {
            while (true) {
                synchronized (this) {
                    while (suspendme) {
                        try {
                            wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                synchronized (u) {
                    System.out.println("in changeObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread {
        public void run() {
            while (true) {
                synchronized (u) {
                    System.out.println("in ReadObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread changeObjectThread = new ChangeObjectThread();
        ReadObjectThread readObjectThread = new ReadObjectThread();
        changeObjectThread.start();
        readObjectThread.start();
        Thread.sleep(1000);
        changeObjectThread.setSuspendme();
        System.out.println("suspend t1 2 sec");
        Thread.sleep(2000);
        System.out.println("resumne t1");
        changeObjectThread.resumeMe();
    }
}
