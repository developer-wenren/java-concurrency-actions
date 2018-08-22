package com.one.lock;

import java.util.concurrent.locks.ReentrantLock;

/*
- 锁中断

- Coding By One ON 08/22
*/
public class IntLcok implements Runnable {
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    private int lock;

    public IntLcok(int lock) {
        this.lock = lock;
    }

    public void run() {
        try {
            if (lock == 1) {
                // 允许中断
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock1.lockInterruptibly();
            }
            System.out.println(Thread.currentThread().getId()+"完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }

            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId()+ "退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntLcok r1 = new IntLcok(1);
        IntLcok r2 = new IntLcok(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t2.interrupt(); //对t2进行锁中断,释放所持有的锁,并退出
        // 这样一来只有 t1获得所有的锁,完成工作
    }
}
