package com.geek.demo05;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author : 赵静超
 * @date : 2020/6/7
 * @description :
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask).start();
        Integer result = (Integer) futureTask.get();
        System.out.println(result);
    }
}

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("调用call()方法...");
        return 1;
    }
}
