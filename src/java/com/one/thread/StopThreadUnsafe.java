package com.one.thread;

/*
- stop的线程

- Coding By One ON 08/15
*/
public class StopThreadUnsafe {
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
        public void run() {
            while (true) {
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
            Thread.sleep(150);
            changeObjectThread.stop();

        }

        /**
         * 输入结果:
         * id:1534291070,name:1534291069
         * id:1534291070,name:1534291069
         * id:1534291070,name:1534291069
         * id:1534291070,name:1534291069
         * id:1534291070,name:1534291069
         *
         * 结论:
         * id和name不一致; 使用stop方法强制停止线程,会导致出现数据不一致问题
         */
    }


}