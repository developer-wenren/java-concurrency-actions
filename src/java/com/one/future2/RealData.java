package com.one.future2;

import java.util.concurrent.Callable;

/**
 * @author One
 * @description
 * @date 2018/10/10
 */
public class RealData implements Callable<String> {
    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<50;i++){
            sb.append(para);
            System.out.println("ddddd");
            Thread.sleep(100);
        }
        return sb.toString();
    }
}
