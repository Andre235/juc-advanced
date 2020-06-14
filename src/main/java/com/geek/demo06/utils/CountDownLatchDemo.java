package com.geek.demo06.utils;

import java.util.concurrent.CountDownLatch;

/**
 * @author : 赵静超
 * @date : 2020/6/7
 * @description : 减法计数器辅助工具类
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " go out");
                //执行减 1 操作
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //等待计数器归零，然后执行下面的代码。否则程序将阻塞在这里。
        countDownLatch.await();
        System.out.println("close door");
    }
}
