package com.one.thread;

/*
- 休眠线程

- Coding By One ON 08/15
*/
public class SleepThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted");
                        break;
                    }

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        System.out.println("Interruted when sleep");
                        // 中断标识:false
                        System.out.println("中断标识:" + Thread.currentThread().isInterrupted());
                        // 抛出的中断异常会清除中断标记,需要额外调用中断方法,来让当前线程退出循环体
                        Thread.currentThread().interrupt();
                        // 中断标识:true
                        System.out.println("中断标识:" + Thread.currentThread().isInterrupted());
                        e.printStackTrace();
                    }
                    Thread.yield();
                }
            }
        };

        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}
