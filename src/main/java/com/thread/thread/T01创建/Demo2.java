package com.thread.thread.T01创建;

/**
 * 方式2：实现Runnable接口，实现run()方法
 */
public class Demo2 implements Runnable {

    public static void main(String[] args) {
        // ThreadDemo2实现Runnable接口，并实现run()
        Demo2 target = new Demo2();
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
