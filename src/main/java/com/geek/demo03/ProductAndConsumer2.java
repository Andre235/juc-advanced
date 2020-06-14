package com.geek.demo03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : 赵静超
 * @date : 2020/6/6
 * @description : 用Lock锁解决生产者消费者进程之间的通信问题
 */
@SuppressWarnings("all")
public class ProductAndConsumer2 {
    public static void main(String[] args) {
        Data2 data = new Data2();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"生产线程1").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"消费线程1").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"生产线程2").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"消费线程2").start();
    }
}

class Data2{
    private int num = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    // 生产操作
    void increment() throws InterruptedException {
        // Condition对象操作进程的等待和唤醒
        lock.lock();
        try {
            while(num != 0){
                // 进入等待状态
                condition.await();
            }
            num ++;
            System.out.println(Thread.currentThread().getName() + "-->" + num);
            // 通知其他线程，我生产完毕
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 消费操作
    void decrement() throws InterruptedException {
        lock.lock();

        try {
            while(num == 0){
                // 进入等待状态
                condition.await();
            }
            num --;
            System.out.println(Thread.currentThread().getName() + "-->" + num);
            // 通知其他线程，我消费完毕
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
