package com.geek.demo02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : 赵静超
 * @date : 2020/6/6
 * @description : ReentrantLock锁的用法
 * 步骤：
 * 1. new Lock();
 * 2. 加锁
 * 3. 解锁
 */
@SuppressWarnings("all")
public class ReentrantLockDemo {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"C").start();

    }
}

class Ticket2{
    private int totalNum = 30;
    private int saledNum = 0;

    Lock lock = new ReentrantLock();

    void sale(){
        lock.lock();

        try {
            saledNum ++;
            if(totalNum - saledNum >= 0){
                System.out.println("线程" + Thread.currentThread().getName() + "卖了" + saledNum + "张票，剩余" + (totalNum - saledNum) +"张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
