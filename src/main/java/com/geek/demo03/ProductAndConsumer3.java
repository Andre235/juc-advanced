package com.geek.demo03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : 赵静超
 * @date : 2020/6/6
 * @description : 用Lock锁实现多个线程之间唤醒指定线程
 */
@SuppressWarnings("all")
public class ProductAndConsumer3 {
    public static void main(String[] args) {
        Data3 data = new Data3();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        },"线程A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        },"线程B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        },"线程C").start();

    }
}

@SuppressWarnings("all")
class Data3{
    private int num = 1;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    void printA(){
        lock.lock();
        try {
            while (num != 1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "--->" + "AAA");
            num = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void printB(){
        lock.lock();
        try {
            while (num != 2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "--->" + "BBB");
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void printC(){
        lock.lock();
        try {
            while (num != 3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "--->" + "CCC");
            num = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
