package com.one.future2;

import java.util.concurrent.*;

/**
 * @author One
 * @description
 * @date 2018/10/10
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //构建FutureTask
        FutureTask<String> task = new FutureTask<>(new RealData("abc"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(task);
        System.out.println("请求完毕");
        try {
            System.out.println("do other work");
            Thread.sleep(2000);
            // 取消任务
//            task.cancel(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("数据=" + task.get(1, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
