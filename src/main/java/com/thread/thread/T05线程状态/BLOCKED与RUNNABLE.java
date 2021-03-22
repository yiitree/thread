package com.thread.thread.T05线程状态;

import org.junit.Test;

/**
 * BLOCKED与RUNNABLE状态的转换
 */
public class BLOCKED与RUNNABLE {

    /**
     * 同步方法争夺锁
     */
    private synchronized void testMethod() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初看之下，大家可能会觉得线程a会先调用同步方法，同步方法内又调用了Thread.sleep()方法，必然会输出TIMED_WAITING，而线程b因为等待线程a释放锁所以必然会输出BLOCKED。
     * 其实不然，有两点需要值得大家注意，一是在测试方法blockedTest()内还有一个main线程，二是启动线程后执行run方法还是需要消耗一定时间的。
     * 测试方法的main线程只保证了a，b两个线程调用start()方法（转化为RUNNABLE状态），如果CPU执行效率高一点，还没等两个线程真正开始争夺锁，就已经打印此时两个线程的状态（RUNNABLE）了。
     * 当然，如果CPU执行效率低一点，其中某个线程也是可能打印出BLOCKED状态的（此时两个线程已经开始争夺锁了）。
     * 这时你可能又会问了，要是我想要打印出BLOCKED状态我该怎么处理呢？BLOCKED状态的产生需要两个线程争夺锁才行。那我们处理下测试方法里的main线程就可以了，让它“休息一会儿”，调用一下Thread.sleep()方法。
     * 这里需要注意的是main线程休息的时间，要保证在线程争夺锁的时间内，不要等到前一个线程锁都释放了你再去争夺锁，此时还是得不到BLOCKED状态的。
     *
     * 主线程main开始执行，然后调用a、b线程的start()方法，ab都变成runnable状态，此时主线程继续执行，
     * 情况1：主线程执行比较快，ab线程都还没有开始执行，都还没进run方法，此时都为runnable
     * 情况2：主线程执行比较慢，此时ab已经有一个线程进入到方法里，所以此时就会一个runnable，另一个只好等先进去的执行完，为blocked状态
     */
    @Test
    public void blockedTest1() {

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
        b.start();
        System.out.println(a.getName() + ":" + a.getState()); // 输出？
        System.out.println(b.getName() + ":" + b.getState()); // 输出？
        //a:RUNNABLE
        //b:RUNNABLE
        // 也会：
        //a:RUNNABLE
        //b:BLOCKED
    }

    /**
     * 情况2：
     * 此时加了一个暂停，所以肯定有时间进入到ab线程里了，所以就分析抢到线程情况
     * a一定进去了，然后又要停顿2s，所以a:TIMED_WAITING，由于主线程停顿1s，所以b进去的时候a还在等待中，所以b至少blocked
     * a的状态转换过程：RUNNABLE（a.start()） -> TIMED_WATING（Thread.sleep()）->RUNABLE（sleep()时间到）->BLOCKED(未抢到锁) -> TERMINATED
     * b的状态转换过程：RUNNABLE（b.start()) -> BLOCKED(未抢到锁) ->TERMINATED
     *
     * 汇总：
     * 第一步：分析ab是否可以进去run方法内部，因为主线程执行很快，主线程关闭后
     */
    @Test
    public void blockedTest2() throws InterruptedException {
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
        // 需要注意这里main线程休眠了1000毫秒，而testMethod()里休眠了2000毫秒
        Thread.sleep(1000L);
        b.start();

        System.out.println(a.getName() + ":" + a.getState()); // 输出？
        System.out.println(b.getName() + ":" + b.getState()); // 输出？
        //a:TIMED_WAITING
        //b:BLOCKED
        // 或
        //a:TIMED_WAITING
        //b:RUNNABLE

    }

    @Test
    public void blockedTest3() throws InterruptedException {

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
        Thread.sleep(1000L);
        a.start();
         // 需要注意这里main线程休眠了1000毫秒，而testMethod()里休眠了2000毫秒
        b.start();

        System.out.println(a.getName() + ":" + a.getState()); // 输出？
        System.out.println(b.getName() + ":" + b.getState()); // 输出？
    }

    @Test
    public void blockedTest4() throws InterruptedException {

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
//        Thread.sleep(1000L); // 需要注意这里main线程休眠了1000毫秒，而testMethod()里休眠了2000毫秒
        b.start();
        System.out.println(a.getName() + ":" + a.getState()); // 输出？
        System.out.println(b.getName() + ":" + b.getState()); // 输出？
    }

    @Test
    public void blockedTest5() throws InterruptedException {

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
//        Thread.sleep(1000L); // 需要注意这里main线程休眠了1000毫秒，而testMethod()里休眠了2000毫秒
        b.start();
        System.out.println(a.getName() + ":" + a.getState()); // 输出？
        System.out.println(b.getName() + ":" + b.getState()); // 输出？
    }



}
