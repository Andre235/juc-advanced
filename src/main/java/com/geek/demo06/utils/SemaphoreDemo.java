package com.geek.demo06.utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author : 赵静超
 * @date : 2020/6/7
 * @description : 信号量
 */
public class SemaphoreDemo {

    //信号量
    //需求：模拟抢车位游戏，6辆汽车抢占3个车位
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            final int temp = i;
            new Thread(()->{
                try {
                    //得到资源
                    semaphore.acquire();
                    System.out.println("汽车" + temp + "抢到了车位...");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("汽车" + temp + "离开了车位...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放资源
                    semaphore.release();
                }
            },"汽车" + i).start();
        }
    }
}
