package com.one.future;

/**
 * @author One
 * @description
 * @date 2018/10/10
 */
public class RealData {
    public final String result;

    public RealData(String result) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 100; i++) {
            sb.append(result);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.result = sb.toString();
    }

    public String getResult() {
        return result;
    }
}
