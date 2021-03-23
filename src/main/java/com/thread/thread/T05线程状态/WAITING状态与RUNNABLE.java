package com.thread.thread.T05线程状态;

import org.junit.Test;

/**
 * BLOCKED与RUNNABLE状态的转换
 */
public class WAITING状态与RUNNABLE {

    /**
     * 同步方法争夺锁
     */
    private synchronized void testMethod() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 要是没有调用join方法，main线程不管a线程是否执行完毕都会继续往下走。
     * a线程启动之后马上调用了join方法，这里main线程就会等到a线程执行完毕，所以这里a线程打印的状态固定是TERMINATED。
     * 至于b线程的状态，有可能打印RUNNABLE（尚未进入同步方法），也有可能打印TIMED_WAITING（进入了同步方法）。
     */
    @Test
    public void blockedTest1() throws InterruptedException {

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "a");
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "b");

        a.start();
        // 必须等a先执行完毕，然后继续后面线程，所以主线程一定会等a先执行完，但是b有没有执行主线程就不管了，自己走完就结束了
        a.join();
        b.start();
        System.out.println(a.getName() + ":" + a.getState()); // 输出？
        System.out.println(b.getName() + ":" + b.getState()); // 输出？
        //a:TERMINATED
        //b:TIMED_WAITING
        // 也会：
        //a:RUNNABLE
        //b:BLOCKED
    }


}
