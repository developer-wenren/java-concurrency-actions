package com.one.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author One
 * @description
 * @date 2018/10/10
 */
public class Producer {
    // 环形缓冲区
    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer byteBuffer) {
        long se = ringBuffer.next();
        try {
            PCData data = ringBuffer.get(se);
            data.setValue(byteBuffer.getLong(0));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ringBuffer.publish(se);
        }
    }
}
