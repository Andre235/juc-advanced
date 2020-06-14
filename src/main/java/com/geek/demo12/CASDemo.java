package com.geek.demo12;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : Jeffersonnn
 * @date : 2020/6/14 17:22
 * @description :
 * CAS(compare and swap)即比较并交换
 */
@SuppressWarnings("all")
public class CASDemo {

    private static AtomicStampedReference<Integer> atomicReference = new AtomicStampedReference<>(1,1);

    public static void main(String[] args) {


        new Thread(()->{
            int stamp = atomicReference.getStamp();
            System.out.println("a ---> " + stamp);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicReference.compareAndSet(1, 2, atomicReference.getStamp(), atomicReference.getStamp() + 1);
            System.out.println("a1 ---> " + atomicReference.getStamp());
            atomicReference.compareAndSet(2, 1, atomicReference.getStamp(), atomicReference.getStamp() + 1);
            System.out.println("a2 ---> " + atomicReference.getStamp());

        },"a").start();

        new Thread(()->{
            int stamp = atomicReference.getStamp();
            System.out.println("b ---> " + stamp);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReference.compareAndSet(1, 2, stamp, stamp + 1);
            System.out.println("b1 ---> " + atomicReference.getStamp());
        },"b").start();

        ReentrantLock reentrantLock = new ReentrantLock();
    }
}
