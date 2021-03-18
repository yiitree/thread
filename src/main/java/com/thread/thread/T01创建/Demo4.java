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
    }

}
