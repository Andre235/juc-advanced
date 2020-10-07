package com.geek.juc;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @Author: geek
 * @Date: 2020/10/7 11:24
 * @Description: 等待通知机制demo
 */
public class WaitNotifyDemo {

    private static boolean flag = true;
    private static final Object LOCK = new Object();

    @SneakyThrows
    public static void main(String[] args) {
        new Thread(new WaitThread(), "WaitThread").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(new NotifyThread(), "NotifyThread").start();
    }

    //等待线程(消费者)
    static class WaitThread implements Runnable{
        @Override
        @SneakyThrows
        public void run() {
            synchronized (LOCK){
                //满足条件则等待
                while (flag){
                    System.out.println(Thread.currentThread() + "flag is true,wait @" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
                    LOCK.wait();
                }
                System.out.println(Thread.currentThread() + "flag is false,do something @" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
            }
        }
    }

    //通知线程(生产者)
    static class NotifyThread implements Runnable{
        @Override
        @SneakyThrows
        public void run() {
            synchronized (LOCK){
                System.out.println(Thread.currentThread() + "hold lock,notify @" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
                LOCK.notifyAll();
                flag = false;
                Thread.sleep(5);
            }

            //再次加锁
//            synchronized (lock){
//                System.out.println(Thread.currentThread() + "hold lock again,notify @" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
//                Thread.sleep(5);
//            }
        }
    }
}
