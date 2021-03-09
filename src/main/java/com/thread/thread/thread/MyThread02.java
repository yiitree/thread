package com.thread.thread.thread;

/**
 * @Author: 曾睿
 * @Date: 2021/3/9 14:40
 */
public class MyThread02 {

    public static void main(String[] args) {

        // ----------------------方式1开始----------------------
        // 直接调用Thread的无参构造
        new Thread(){
            @Override
            public void run() {
                System.out.println("The code waiting for Thread1");
            }
        }.start();

        // lambda
        new Thread(
                () -> System.out.println("The code waiting for Thread1")
        ).start();


        // ----------------------方式1结束----------------------

        // ----------------------方式2开始----------------------
        new Thread(
                // 调用有参构造，传递实现Runnable()接口的类
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("The code waiting for Thread2");
                    }
        }).start();
        // ----------------------方式2结束----------------------

        // ----------------------方式3开始----------------------
        // 方式3（多个线程共享）：
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("The code waiting for Threads");
            }
        };
        Thread t1  = new Thread(r);
        Thread t2  = new Thread(r);
        Thread t3  = new Thread(r);
        Thread t4  = new Thread(r);
        Thread t5  = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        // ----------------------方式3结束----------------------

    }
}
