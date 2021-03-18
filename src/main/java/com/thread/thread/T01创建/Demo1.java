package com.thread.thread.T01创建;

/**
 * 方式1：继承Thread类，重写run()方法
 */
public class Demo1 extends Thread {

    public static void main(String[] args) {
        // ThreadDemo1继承了Thread类，并重写run()
        Demo1 t = new Demo1();
        // 开启线程：t线程得到CPU执行权后会执行run()中的代码
        t.start();
    }

    @Override
    public void run() {
        System.out.println("Thread is running");
    }
}
