package com.geek.juc;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Author: geek
 * @Date: 2020/10/7 13:47
 * @Description: 一个线程可以根据ThreadLocal对象查询绑定到该线程上的值
 */
public class ThreadLocalDemo {
    private static final ThreadLocal<Long> TIME_THREAD_LOCAL = new ThreadLocal<Long>(){
        protected Long initialTime(){
            return System.currentTimeMillis();
        }
    };

    private static void begin(){
        TIME_THREAD_LOCAL.set(System.currentTimeMillis());
    }

    private static Long end(){
        return System.currentTimeMillis() - TIME_THREAD_LOCAL.get();
    }

    @SneakyThrows
    public static void main(String[] args) {
        ThreadLocalDemo.begin();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Cost: " + ThreadLocalDemo.end() + " mills");
    }
}
