package com.thread.thread.线程安全;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 写一个固定容量的同步容器：
 * 拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * @Author: 曾睿
 * @Date: 2021/3/9 16:53
 */
public class MyContainer1<T> {

    /**
     * 设置容器
     * 首先，能想到在MyContainer中塞入LinkedList作为容器
     * 因为有removeFirst方法，比较方便
     */
    final private LinkedList<T> lists = new LinkedList<>();
    /** 固定容量,假定最多10个元素 */
    final private int MAX = 10;
    private int count = 0;

    /**
     * put方法
     * @param t
     */
    public synchronized void put(T t) {
        /*
         * 想想为什么用while而不是用if？
         * While的话，线程被唤醒后得到执行权会再次进行判断，这在高并发情景下是必须的。
         * 因为在这个线程休眠期间，极有可能条件已经不成立，此时不应该继续向下执行。如果重新读取判断条件，即可避免重复执行。
         */
        while(lists.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        /**
         * 通知消费者线程进行消费
         * wait方法必须配合notifyAll。
         * 据说《Effective Java》甚至提出，wait在绝大多数场景下应该伴随着notifyAll而不是notify。
         * 因为notify的唤醒是随机，不能确定唤醒的是哪个线程（可能是消费者方，也可能是生产者方）。
         */
        this.notifyAll();
    }

    /**
     * get方法
     * @return
     */
    public synchronized T get() {
        T t = null;
        while(lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count --;
        //通知生产者进行生产
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();
        //启动消费者线程
        for(int i=0; i<10; i++) {
            new Thread(()->{
                for(int j=0; j<5; j++){
                    System.out.println(c.get());
                }
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for(int i=0; i<2; i++) {
            new Thread(()->{
                for(int j=0; j<25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }

    }
}
