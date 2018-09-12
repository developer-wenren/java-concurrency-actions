package com.one.threadpool;

import java.util.concurrent.*;

/*
- 扩展线程池

- Coding By One ON 09/12
*/
public class ExtensionThreadPoolLab {
    public static class MyTask implements Runnable {
        public String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("正在执行 " + ":ThreadId:" + Thread.currentThread().getId() + " , TaskName: " + name);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new MyThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        for (int i=0;i<5;i++){
            MyTask task = new MyTask("Task-geym-"+i);
            threadPoolExecutor.submit(task);
            Thread.sleep(10);
        }
        threadPoolExecutor.shutdown();
    }

}

class MyThreadPoolExecutor extends ThreadPoolExecutor {
    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("准备执行 :" + t.getId());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("执行结束" +r);
    }

    @Override
    protected void terminated() {
        System.out.println("线程池退出 :");
    }
}
