package com.one.future;

/**
 * @author One
 * @description 客户端
 * @date 2018/10/10
 */
public class Client {
    public Data request(final String queryStr) {
        final FutureData futureData = new FutureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }).start();
        return futureData;
    }

    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("data " + data.getResult());
    }
}
