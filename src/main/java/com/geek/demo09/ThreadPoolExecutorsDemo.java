package com.geek.demo09;

import java.util.concurrent.*;

/**
 * @author : 赵静超
 * @date : 2020/6/10
 * @description :
 * 四种拒绝策略：
 * AbortPolicy：队列满了则抛出异常
 * CallerRunsPolicy：队列满了谁叫你调的我则 谁去执行(交个main线程去执行)
 * DiscardPolicy：队列满了，则直接抛弃，不会抛出异常
 * DiscardOldestPolicy：队列满了，则会判断最早执行的线程有没有空闲，如有空闲则交个它执行
 */
@SuppressWarnings("all")
public class ThreadPoolExecutorsDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        try {
            for (int i = 1; i <= 9; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {

        } finally {
            //关闭线程池
            threadPool.shutdownNow();
        }
    }
}
