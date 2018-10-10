package com.one.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author One
 * @description
 * @date 2018/10/10
 */
public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
