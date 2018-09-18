package com.one.collection;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author One
 * @description 阻塞队列实验
 * 解耦生产者和消费者
 * take()当元素为空时阻塞;
 * put()当元素满时阻塞
 * @date 2018/09/18
 */
public class BlockingQueueLab {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(2);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    try {
                        arrayBlockingQueue.put("" + i);
                        System.out.println("arrayBlockingQueue put " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    try {
                        Object take = arrayBlockingQueue.take();
                        System.out.println("arrayBlockingQueue take " + take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        thread.start();
        System.out.println("done");
    }
}
