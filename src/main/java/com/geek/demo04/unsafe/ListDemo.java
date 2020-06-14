package com.geek.demo04.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author : 赵静超
 * @date : 2020/6/7
 * @description : 并发下ArrayList是不安全的
 * 解决方案：
 *      1.使用线程安全的Vector代替ArrayList
 *      List<String> list = new Vector<>();
 *      2.使ArrayList集合变的安全
 *      Collections.synchronizedList(new ArrayList<>());
 *      3.使用JUC并发集合实现 CopyOnWriteArrayList
 *      COW思想，写入时复制。
 *      原理：
 *          使用transient volatile关键字修饰Array
 *          使用ReentrantLock对对象加锁，在写入前先复制一份，将元素插入到复制的list中，然后再将插入后的list写入到原来的list中
 */
public class ListDemo {

    //List<String> list = new Vector<>();
    //List<String> list = Collections.synchronizedList(new ArrayList<>());
    //List<String> list = new CopyOnWriteArrayList<>();
    public static void main(String[] args) {
        // ConcurrentModificationException
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list.toString());
            }).start();
        }
    }
}
