package com.one.lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
* 倒计时器实验
* CountDownLatch.await() 要求等待所有计数任务完成才能继续执行
* Coding By One ON 09/11
*/
public class CountDownLatchLab implements  Runnable{
    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchLab lab = new CountDownLatchLab();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check complete");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++) {
            executorService.submit(lab);
        }
        // 等待检查
        end.await();
        System.out.println("Fire");
        executorService.shutdown();
        /**
         * check complete
         * ... x10
         * Fire
         */
    }
}
