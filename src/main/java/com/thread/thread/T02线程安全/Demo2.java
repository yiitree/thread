package com.thread.thread.T02线程安全;

/**
 * 多线程安全问题
 * 解析：
 * cnt++分为两步：
 * 第一步：从内存中取cnt
 * 第二步：到栈帧空间
 *
 */
public class Demo2 {

    /** 共享数据cnt */
    static int cnt = 0;

    /**
     * 会引起安全问题分析 ：++不是原子操作，
     */
    public static void main(String[] args) {
        Runnable r = () -> {
                //有多条语句操作共享数据
                int n = 5;
                while (n > 0) {
                    int tmp = cnt + 1;
                    cnt = tmp;
                    n--;
                }
            };
        // 多线程环境
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        try {
            //等待足够长的时间 确保上述线程均执行完毕
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cnt);
    }

}
