package com.thread.thread.thread;

/**
 * @Author: 曾睿
 * @Date: 2021/3/9 14:40
 */
public class MyThread03 {

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
                System.out.println("Thread's run method is running"); }
        }.start();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(1111);
            }
        };
        Runnable runnable1 = () -> System.out.println(1111);

        Thread thread = new Thread(runnable){
            @Override
            public void run() {
                super.run();
                System.out.println(1);
            }
        };
        thread.start();



    }
}
