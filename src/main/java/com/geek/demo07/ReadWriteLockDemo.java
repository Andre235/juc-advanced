package com.geek.demo07;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @author : 赵静超
 * @date : 2020/6/7
 * @description : 读写锁案例(在没有加读写锁的情况下会出现 写操作插队的情况)
 */
@SuppressWarnings("all")
public class ReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {
        MyMap myMap = new MyMap();
        for (int i = 1; i <= 6; i++) {
            final int temp = i;
            new Thread(()->{
                myMap.write(temp, UUID.randomUUID().toString().substring(0,5));
            },"线程" + i).start();
        }
        for (int i = 1; i <= 6; i++) {
            final int temp = i;
            new Thread(()->{
                myMap.read(temp);
            },"线程" + i).start();
        }

    }
}

class MyMap{
    volatile Map<Integer,String> map = new HashMap<>();

    public Map<Integer, String> getMap() {
        return map;
    }

    void read(Integer key){
        System.out.println(Thread.currentThread().getName() + "读取的key为：" + key);
        String result = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取key的结果为：" + result);
    }

    void write(Integer key,String value){
        System.out.println(Thread.currentThread().getName() + "执行写操作，key为：" + key + "，value为：" + value);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName() + "执行写操作完毕");
    }
}
