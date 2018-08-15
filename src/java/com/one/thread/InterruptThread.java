package com.one.thread;

/*
- 线程的中断
- Coding By One ON 08/15
*/
public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                // 写法一
                while (true && !Thread.currentThread().isInterrupted()) {
                    // 写法二
//                    if (Thread.currentThread().isInterrupted()) {
//                        System.out.println("线程工作中");
//                        break;
//                    }

                    System.out.println("线程工作中");
                    Thread.yield();
                }
                System.out.println("线程中断了");
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }

}
