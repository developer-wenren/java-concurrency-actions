package com.one.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
 * 信号量实验
 * Coding By One ON 09/10
 */
public class SemaphoreLab implements Runnable {
    final Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + "is done !");
            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final SemaphoreLab semaphoreLab = new SemaphoreLab();
        for (int i = 0; i < 20; i++) {
            executorService.submit(semaphoreLab);
            // 5个为一组输出
        }
        System.out.println("end");
    }
}
