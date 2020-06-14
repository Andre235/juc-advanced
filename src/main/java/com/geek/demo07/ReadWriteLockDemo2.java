package com.geek.demo07;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : 赵静超
 * @date : 2020/6/7
 * @description : 读写锁案例(加入读写锁)
 */
@SuppressWarnings("all")
public class ReadWriteLockDemo2 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        MyMap1 myMap = new MyMap1();

        for (int i = 1; i <= 6; i++) {
            final int temp = i;
            new Thread(()->{
                myMap.write(temp, UUID.randomUUID().toString().substring(0,5));
                latch.countDown();
            },"线程" + i).start();
        }

        latch.await();

        for (int i = 1; i <= 6; i++) {
            final int temp = i;
            new Thread(()->{
                myMap.read(temp);
            },"线程" + (i + 6)).start();
        }
    }
}

class MyMap1{

    volatile Map<Integer,String> map = new HashMap<>();
    //创建读写锁对象
    ReentrantReadWriteLock  readWriteLock = new ReentrantReadWriteLock();

    public Map<Integer, String> getMap() {
        return map;
    }

    void read(Integer key){
        //给读锁加锁
        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "读取的key为：" + key);
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取key的结果为：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //给读锁解锁
            readWriteLock.readLock().unlock();
        }
    }

    void write(Integer key,String value){
        //给写锁加锁
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "执行写操作，key为：" + key + "，value为：" + value);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "执行写操作完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //给写锁解锁
            readWriteLock.writeLock().unlock();
        }
    }
}
