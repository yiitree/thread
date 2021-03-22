package com.thread.thread.T03CallableFuture与FutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用Callable创建
 * 有返回值的线程
 */
// 自定义Callable
class Demo1 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        // 模拟计算需要一秒
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String args[]) throws Exception {
        // 使用

        ExecutorService executor = Executors.newCachedThreadPool();
        Demo1 task = new Demo1();
        Future<Integer> result = executor.submit(task);
        // 注意调用get方法会阻塞当前线程，直到得到结果。
        // 所以实际编码中建议使用可以设置超时时间的重载get方法。
        System.out.println(result.get());
    }

}
