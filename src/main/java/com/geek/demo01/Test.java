package com.geek.demo01;

/**
 * @author : 赵静超
 * @date : 2020/6/5
 * @description : synchronized关键字的用法
 */
@SuppressWarnings("all")
public class Test {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
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

class Ticket{
    private int totalNum = 30;
    private int saledNum = 0;
    synchronized void sale(){
        saledNum ++;
        if(totalNum - saledNum >= 0){
            System.out.println("线程" + Thread.currentThread().getName() + "卖了" + saledNum + "张票，剩余" + (totalNum - saledNum) +"张票");
        }
    }
}
