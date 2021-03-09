package com.thread.thread.thread;

/**
 * 创建多线程的2种常见方式
 */
public class MyThread {}

/**
 * 方式1：继承Thread类，重写run()方法
 */
class ThreadDemo1 extends Thread {
    public static void main(String[] args) {
        // ThreadDemo1继承了Thread类，并重写run()
        ThreadDemo1 t = new ThreadDemo1();
        // 开启线程：t线程得到CPU执行权后会执行run()中的代码
        t.start();
    }

    @Override
    public void run() {
        System.out.println("Thread is running");
    }
}

/**
 * 方式2：实现Runnable接口，实现run()方法
 */
class ThreadDemo2 implements Runnable{
    public static void main(String[] args) {
        // ThreadDemo2实现Runnable接口，并实现run()
        ThreadDemo2 target = new ThreadDemo2();
        // 调用Thread构造方法，传入TreadDemo2的实例对象，创建线程对象
        Thread t = new Thread(target);
        // 开启线程：t线程得到CPU执行权后会执行run()中的代码
        t.start();
    }

    @Override
    public void run() {
        System.out.println("Thread is running");
    }
}
