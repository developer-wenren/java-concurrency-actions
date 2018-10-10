package com.one.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author One
 * @description
 * @date 2018/10/10
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        PCDataFactory factory = new PCDataFactory();
        int buffferSize = 1024;
        Disruptor<PCData> pcDataDisruptor = new Disruptor<>(factory,
                buffferSize,
                executor,
                ProducerType.MULTI,
                new BlockingWaitStrategy());
        pcDataDisruptor.handleEventsWithWorkerPool(
                new Consumer(),
                new Consumer(),
                new Consumer(),
                new Consumer()
        );
        pcDataDisruptor.start();

        RingBuffer<PCData> ringBuffer = pcDataDisruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0;true;l++){
            bb.putLong(0,l);
            producer.pushData(bb);
            Thread.sleep(100);
            System.out.println("add data " + l);
        }
    }
}
