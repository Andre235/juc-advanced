package com.geek.demo03;

/**
 * @author : 赵静超
 * @date : 2020/6/6
 * @description : 生产者消费者问题
 * 线程之间的通信：生产者消费者问题。生产线程操作完毕后通知消费线程去消费，不满足生产条件时 生产线程处于等待状态。消费线程消费完成后，通知生产线程。
 * 如果是多个线程之间通信，不进行合适的判断，则会出现虚假唤醒的情况
 */
@SuppressWarnings("all")
public class ProductAndConsumer {
    public static void main(String[] args) {
        Data data = new Data();

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

class Data{
    private int num = 0;

    // 生产操作
    synchronized void increment() throws InterruptedException {
        while(num != 0){
            // 进入等待状态
            this.wait();
        }
        num ++;
        System.out.println(Thread.currentThread().getName() + "-->" + num);
        // 通知其他线程，我生产完毕
        this.notify();
    }

    // 消费操作
    synchronized void decrement() throws InterruptedException {
        while(num == 0){
            // 进入等待状态
            this.wait();
        }
        num --;
        System.out.println(Thread.currentThread().getName() + "-->" + num);
        // 通知其他线程，我消费完毕
        this.notify();
    }
}
