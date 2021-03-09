package com.thread.thread.thread;

/**
 * 多线程安全问题
 *
 * @Author: 曾睿
 * @Date: 2021/3/9 14:40
 */
public class MyThread04 {

    /** 共享数据cnt */
    static int cnt = 0;

    /**
     * 会引起安全问题
     * @param args
     */
    public static void main(String[] args) {
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                //有多条语句操作共享数据
//                int n = 10000;
//                while (n > 0) {
//                    cnt++;
//                    n--;
//                }
//            }
//        };

        Runnable r = () -> {
                //有多条语句操作共享数据
                int n = 10000;
                while (n > 0) {
//                    cnt++;
                    cnt = cnt + 1;
                    n--;
                }
            };
        //多线程环境
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        Thread t4 = new Thread(r);
        Thread t5 = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        try {
            //等待足够长的时间 确保上述线程均执行完毕
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出的结果会小于50000
        System.out.println(cnt);
    }

}
