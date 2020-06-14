package com.geek.demo08;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : 赵静超
 * @date : 2020/6/7
 * @description : 同步队列
 * 特点：put一个元素后必须take后才能 put下一个元素
 */
public class QueueDemo {
    public static void main(String[] args) {

        SynchronousQueue<String> queue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "put " + "1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName() + "put " + "2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName() + "put " + "3");
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "take " + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "take " + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "take " + queue.take());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"T2").start();
    }
}
