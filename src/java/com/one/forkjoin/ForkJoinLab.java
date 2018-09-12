package com.one.forkjoin;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/*
- Fork/Join 模式采用分而治之
- 容易造成线程数量堆积,导致性能下降
- 函数调用层次过深导致栈溢出
- Coding By One ON 09/12
*/
public class ForkJoinLab {
    public static class CountTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 10000;
        private long start;
        private long end;

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            boolean canCompute = (end - start) < THRESHOLD;
            if (canCompute) {
                for (long u = start; u <= end; u++) {
                    sum += u;
                }
            } else {
                long step = (start+end)/100;
                ArrayList<CountTask> taskArrayList = new ArrayList<>();
                long pos = start;
                for (int i=0;i<100;i++){
                    long lastOne = pos+step;
                    if (lastOne>end) {
                        lastOne=end;
                    }
                    CountTask task = new CountTask(pos,lastOne);
                    pos+=step+1;
                    taskArrayList.add(task);
                    task.fork(); // 提交子任务
                }

                for (CountTask t:taskArrayList){
                    sum += t.join(); // 子任务结果
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        CountTask task = new CountTask(0, 210000L);
        ForkJoinTask<Long> submit = pool.submit(task);
        try {
            long res = submit.get();
            System.out.println("sum = " + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
