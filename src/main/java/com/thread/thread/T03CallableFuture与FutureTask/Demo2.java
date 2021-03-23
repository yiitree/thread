package com.thread.thread.T03CallableFuture与FutureTask;

import java.util.concurrent.*;

/**
 * 在很多高并发的环境下，有可能Callable和FutureTask会创建多次。
 * FutureTask能够在高并发环境下确保任务只执行一次。
 */
class Demo2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        // 模拟计算需要一秒
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String args[]) throws Exception {
        // 使用

        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<>(new Demo2());
        executor.submit(futureTask);
        System.out.println(futureTask.get());
    }

}
