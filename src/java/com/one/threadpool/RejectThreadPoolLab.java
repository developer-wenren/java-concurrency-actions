package com.one.threadpool;

import java.util.concurrent.*;

/*
- 自定义线程池
- Coding By One ON 09/11
*/
public class RejectThreadPoolLab {
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        //  自定义拒绝策略
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10), Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString() + " is discard");
                    }
                });
        for (int i=0;i< Integer.MAX_VALUE;i++) {
            executor.submit(task);
            Thread.sleep(10);
        }
    }
}
