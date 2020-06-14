package com.geek.demo11;

import java.util.concurrent.TimeUnit;

/**
 * @author : 赵静超
 * @date : 2020/6/14
 * @description : Volatile关键字的使用（保证共享变量的内存可见性）
 *
 * 反例
 * private static int num = 0;
 * 由于num没有用volatile关键字修饰，在主存与线程的工作内存 不可见
 * 主线程将num重新赋值为2，但是由于线程1内存不可见的问题，不能同步到num值的变化，因此程序进入死循环状态\
 *
 * 正例
 * private static volatile int num = 0;
 * 解决思路：让线程1知道主存中的共享变量发生了变化
 * 解决方案：使用volatile关键字修饰 共享变量num
 */
@SuppressWarnings("all")
public class VolatileDemo {

    private static volatile int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (num == 0){
            }
        },"线程1").start();
        TimeUnit.SECONDS.sleep(3);

        num = 2;
        System.out.println("num值为：" + num);
    }
}
