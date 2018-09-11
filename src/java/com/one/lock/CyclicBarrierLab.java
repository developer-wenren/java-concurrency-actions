package com.one.lock;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
 * 循环栅栏
 * 也能实现线程间的计数等待
 * 并且计数器到零后会重置
 * Coding By One ON 09/11
 */
public class CyclicBarrierLab {
    public static class Soldier implements Runnable {
        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        public Soldier(CyclicBarrier cyclicBarrier, String soldier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                doWork();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }

        private void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + "任务完成");
        }
    }

    public static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int N) {
            this.flag = flag;
            this.N = N;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("士兵" + N + "个任务完成");
            } else {
                System.out.println("士兵" + N + "个集合完毕");
                flag = true;
            }
        }
    }

    /**
     * 集合
     * 士兵0报道
     * ...
     * 士兵9报道
     * 士兵10个集合完毕
     * 士兵9任务完成
     * ...
     * 士兵2任务完成
     * 士兵10个任务完成
     * @param args
     */
    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new BarrierRun(flag, N));
        System.out.println("集合");
        for (int i = 0; i < N; ++i) {
            System.out.println("士兵" + i + "报道");
            allSoldier[i] = new Thread(new Soldier(cyclicBarrier, "士兵" + i));
            allSoldier[i].start();

            if (i == 5) {
                // 触发 1个InterruptedException,9个BrokenBarrierException
                allSoldier[0].interrupt();
            }

        }

    }
}
