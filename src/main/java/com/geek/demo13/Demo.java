package com.geek.demo13;

import java.util.concurrent.TimeUnit;

/**
 * @author : Jeffersonnn
 * @date : 2020/6/14 19:32
 * @description :
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                spinLockDemo.myUnLock();
            }
        },"T1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            spinLockDemo.myLock();
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                spinLockDemo.myUnLock();
            }
        },"T2").start();

    }
}
