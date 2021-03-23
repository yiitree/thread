package com.thread.thread.T02线程安全;

/**
 * 信号量
 * 需求：
 * 比如我现在有一个需求，我想让线程A输出0，然后线程B输出1，再然后线程A输出2…以此类推
 */
public class Signal {

    /**
     * volatile关键字能够保证内存的可见性，如果用volatile关键字声明了一个变量，
     * 在一个线程里面改变了这个变量的值，那其它线程是立马可见更改后的值的。
     * volatile变量需要进行原子操作
     * signal++并不是一个原子操作，所以我们在实际开发中，会根据需要使用synchronized给它“上锁”，
     * 或者是使用AtomicInteger等原子类。
     */
    private static volatile int signal = 0;

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 0) {
                    System.out.println("threadA: " + signal);
                    signal++;
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 1) {
                    System.out.println("threadB: " + signal);
                    signal = signal + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
        // 输出：
        //threadA: 0
        //threadB: 1
        //threadA: 2
        //threadB: 3
        //threadA: 4
    }
}

