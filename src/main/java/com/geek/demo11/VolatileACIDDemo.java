package com.geek.demo11;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : 赵静超
 * @date : 2020/6/14
 * @description :
 * 线程原子性：线程在执行过程中不能被打断，要么全部成功，要么全部失败
 * volatile关键字不保证线程的原子性操作
 *
 * 解决方案：
 *      要求不使用Lock锁和Synchronized关键字来实现线程的原子操作
 *      使用juc包中的原子类可以解决该问题
 */
public class VolatileACIDDemo {

    //private static volatile int num = 0;
    private static AtomicInteger num = new AtomicInteger(1);

    public static void main(String[] args) {
        //开启20条线程
        for (int i = 0; i < 20; i++) {
            //每条线程循环调用1000次 add()方法，预期结果 num = 20000
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        //java程序中最少有2条线程在执行（main线程和GC垃圾回收线程）
        //如果活跃的线程数大于2，说明除了main线程和GC线程外还有线程在工作，则进行礼让，即不执行while循环以下的程序
        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println("num值为 " + num);
    }

    private static void add() {
        //num ++;
        num.getAndIncrement();
    }
}
