package com.thread.thread.T01创建;

/**
 * 运行测试
 */
public class Demo4 {

    public static void main(String[] args) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Runnable's run method is running");
                    }
                }
        ){
            @Override
            public void run() {
                System.out.println("Thread's run method is running");
            }
        }.start();
        // Thread's run method is running


        new Thread(){
            @Override
            public void run() {
                System.out.println("Thread's run method is running");
            }
        }.start();
        // 重写Thread(new Runnable())的构造方法
        // Thread(Runnable对象){重写run} ，相当于run方法被覆盖
        // 不管有几种实现多线程的方式，Thread类永远是入口，Thread的run()永远是开头。代码能否被执行到，全看run()。
        // 你要么写在Thread的run()中，要么让Thread的run()去调到你写的代码(targer.run())。

    }

}
