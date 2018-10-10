package com.one.future;

/**
 * @author One
 * @description
 * @date 2018/10/10
 */
public class FutureData implements Data {
    protected RealData realData = null;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        System.out.println("set" + System.currentTimeMillis());
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {
            System.out.println("get" + System.currentTimeMillis());
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return realData.result;
    }
}
