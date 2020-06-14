package com.geek.demo09;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * @author : 赵静超
 * @date : 2020/6/10
 * @description :
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        //创建只有单条线程的线程池
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //创建固定大小的线程池
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //创建可伸缩大小的线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 100; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {

        } finally {
            //关闭线程池
            threadPool.shutdownNow();
        }
        stream();
    }

    private static void stream() {
    }


}
