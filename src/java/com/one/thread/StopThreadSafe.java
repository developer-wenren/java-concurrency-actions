package com.one.thread;

/*
- 安全停止线程运行

- Coding By One ON 08/15
*/
public class StopThreadSafe {
    public static User u = new User();

    public static class User {
        private int id;
        private String name;

        public User() {
            id = 0;
            name = "0";
        }

        public String toString() {
            return "id:" + id + ",name:" + name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


    public static class ChangeObjectThread extends Thread {
        volatile boolean stop = false;

        public ChangeObjectThread() {
            super();
        }

        public void needStop() {
            stop = true;
        }

        public void run() {
            while (true) {
                if (stop) {
                    System.out.println("exit by stop");
                    break;
                }
                synchronized (u) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    u.setId(v);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    u.setName(String.valueOf(v));

                }

                // 释放CPU调度
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread {
        public void run() {
            while (true) {
                synchronized (u) {
                    if (u.getId() != Integer.parseInt(u.getName())) {
                        System.out.println(u);
                    } else  {
                        System.out.println(Thread.currentThread()+"+数据一致: " + u);
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true) {
            ChangeObjectThread changeObjectThread = new ChangeObjectThread();
            changeObjectThread.start();
            Thread.sleep(250);
//            changeObjectThread.stop();
            changeObjectThread.needStop();
            // 使用 标记变量来指示线程是否需要退出

        }

    }


}