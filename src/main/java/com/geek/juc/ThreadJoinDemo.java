package com.geek.juc;

import lombok.SneakyThrows;

import javax.swing.plaf.TableHeaderUI;

/**
 * @Author: geek
 * @Date: 2020/10/7 13:12
 * @Description: 一个线程只有等前驱线程结束以后，才能够从join()方法中返回
 */
public class ThreadJoinDemo {

    public static void main(String[] args) {
        //main线程
        Thread previous = Thread.currentThread();

        for (int i = 0; i < 10; i++) {
            //每个线程拥有一个前驱线程的引用，只有等待前驱线程终止后，当前线程才能够从join方法中返回
            Thread thread = new Thread(new Demino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
    }

    static class Demino implements Runnable{
        Thread thread;

        public Demino(Thread thread) {
            this.thread = thread;
        }

        @Override
        @SneakyThrows
        public void run() {
            thread.join();
            System.out.println(Thread.currentThread().getName() + " terminated.");
        }
    }
}
