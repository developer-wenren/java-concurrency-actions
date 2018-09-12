package com.one.threadpool;

import java.util.concurrent.*;

/*
- 多线程编程中有异常时出现堆栈信息丢失情况,需要扩展实现有堆栈信息的线程池
- Coding By One ON 09/12
*/
public class TraceThreadPoolExecutorLab {
    public static void main(String[] args) {
        TraceThreadPoolExecutor executor = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for (int i=0;i<5;i++){
            executor.execute(new DivTask(100,i));
        }


    }

    public static class TraceThreadPoolExecutor extends ThreadPoolExecutor {

        public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        public void execute(Runnable command) {
            Runnable warp = warp(command, clientTrace(), Thread.currentThread().getName());
            super.execute(warp);
        }

        private Runnable warp(Runnable command, final Exception clientTrace, String name) {
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        command.run();
                    } catch (Exception e) {
                        clientTrace.printStackTrace();
                        throw e;
                    }
                }
            };
        }

        private Exception clientTrace() {
            return new Exception("Client Stack Trace");
        }
    }

    private static class DivTask implements Runnable {
        int a,b;
        public DivTask(int i, int i1) {
            a = i;
            b = i1;
        }

        @Override
        public void run() {
            double re = a/b;
            System.out.println("result : " + re);
        }
    }
}
