package com.one.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 条件实验
 * Coding By One ON 09/10
 * 一个线程对应一个Condition,可以实现和Object的wait()和notify()一样的功能
 * 使用场景: JDK的ArrayBlockQueue
 */
public class ConditionLab {

    public static class ReentrantLockCondition implements Runnable {
        private static ReentrantLock lock= new ReentrantLock();
        public static Condition condition = lock.newCondition();

        public void run() {
            try {
                lock.lock();
                condition.await(); // 等待 singal 唤醒
                System.out.println("Thread is going on");
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockCondition r1 = new ReentrantLockCondition();
        Thread t1 = new Thread(r1);
        t1.start();
        Thread.sleep(2000);
        ReentrantLockCondition.lock.lock();
        ReentrantLockCondition.condition.signal();
        ReentrantLockCondition.lock.unlock();

    }
}
