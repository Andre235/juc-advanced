package com.geek.demo13;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author : Jeffersonnn
 * @date : 2020/6/14 19:24
 * @description :
 * 自定义实现自旋锁
 */
public class SpinLockDemo {

    //传入一个Thread，初始值为null
    AtomicReference<Thread>  atomicReference = new AtomicReference<Thread>();

    //自旋锁加锁
    public void myLock(){
        System.out.println(Thread.currentThread().getName() + " lock");
        //获取当前的线程
        Thread thread = Thread.currentThread();
        while ( ! atomicReference.compareAndSet(null,thread)){

        }
    }
    //自旋锁解锁
    public void myUnLock(){
        System.out.println(Thread.currentThread().getName() + " unLock");
        //获取当前的线程
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
    }
}
