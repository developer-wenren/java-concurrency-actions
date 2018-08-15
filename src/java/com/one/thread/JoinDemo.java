package com.one.thread;

/*
- 线程的join()

- Coding By One ON 08/15
*/
public class JoinDemo {
    private volatile static int i = 0;

    public static class AddThread extends Thread {
        public void run() {
            for (i = 0; i < 1000000; i++) {
//                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread addThread = new AddThread();
        addThread.start();
        addThread.join();
        System.out.println("addThread end");
        System.out.println(i);
    }
}
