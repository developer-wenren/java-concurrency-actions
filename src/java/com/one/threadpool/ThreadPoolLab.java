package com.one.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
- 线程池
- Coding By One ON 09/11
*/
public class ThreadPoolLab {
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "Thread id:" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i=0;i<10;i++) {
            executorService.submit(task);
        }
    }
}
