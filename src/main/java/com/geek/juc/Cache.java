package com.geek.juc;
import java.util.ArrayList;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: geek
 * @Date: 2020/10/8 12:30
 * @Description: 使用读写锁实现一个线程安全的缓存工具类
 * Cache工具类使用的是读写锁，提升了读操作的并发性，同时可以保证写操作对读操作的可见性；读写锁之于排它锁，前者有更好的并发性以及更高的吞吐量。
 */
public class Cache {
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final Lock readLock = readWriteLock.readLock();
    private static final Lock writeLock = readWriteLock.writeLock();
    private static HashMap<String, Object> map = new HashMap<>(300);

    public static Object get(String key){
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public static <V> V getObj(String key, Class<V> clazz){
        readLock.lock();
        try {
            return clazz.cast(map.get(key));
        } finally {
            readLock.unlock();
        }
    }

    public static <E> List<E> getList(String key, Class<E> clazz){
        readLock.lock();
        List<E> list =new ArrayList<E>();
        try {
            return map.get(key) == null ? new ArrayList<E>() : (List<E>) map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public static <V> void set(String key, V value){
        writeLock.lock();
        try {
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public static void clear(){
        writeLock.lock();
        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public static void remove(String key){
        writeLock.lock();
        try {
            map.remove(key);
        } finally {
            writeLock.unlock();
        }
    }
}
