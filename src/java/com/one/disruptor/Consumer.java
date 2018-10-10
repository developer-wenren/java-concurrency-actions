package com.one.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * @author One
 * @description
 * @date 2018/10/10
 */
public class Consumer implements WorkHandler<PCData> {
    @Override
    public void onEvent(PCData pcData) throws Exception {
        System.out.println(Thread.currentThread().getId() + "pcData: --" + pcData.getValue() * pcData.getValue() +
                "--");
    }
}
