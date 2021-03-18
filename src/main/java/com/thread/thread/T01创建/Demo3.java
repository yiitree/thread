package com.thread.thread.T01创建;

/**
 * 方式3：
 * 本质上还是之前两种方式，只是变成匿名内部类
 * 使用lambda进行简写
 */
public class Demo3 {

    public static void main(String[] args) {

        /**
         * 方式1
         * 直接调用Thread的无参构造
         */
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

        /**
         * 方式2
         * 调用有参构造，传递实现Runnable()接口的类
         */
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("The code waiting for Thread2");
                    }
                }).start();

        /**
         * 方式3
         * （多个线程共享）：
         */
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

    }

}
