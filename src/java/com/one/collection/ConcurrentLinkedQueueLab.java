package com.one.collection;

import java.util.concurrent.ConcurrentLinkedQueue;

/*
- ConcurrentLinkedQueue 高并发场景下性能最好的队列
- Coding By One ON 09/12
*/
public class ConcurrentLinkedQueueLab {
    private static ConcurrentLinkedQueue queue;

    public static void main(String[] args) {
        queue = new ConcurrentLinkedQueue();
        queue.add("1");
        queue.add("2");
//        for (int i = 10, j = i; ; ) {
//        }
        boolean remove = queue.remove("2");
        System.out.println(remove);
    }

}
