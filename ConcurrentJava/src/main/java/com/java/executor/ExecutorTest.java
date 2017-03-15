package com.java.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;


public class ExecutorTest {
    
    
    public static void main(String[] args)throws Exception {
        
        /**
         * corePoolSize 核心的线程池大小
         * maximumPoolSize 最大线程池大小
         * keepAliveTime  线程空闲条件下存活的时间 
         * unit           指定存活时间的单位
         * workQueue      存放任务的队列 
         *                1.ArrayBlockingQueue   有界阻塞
         *                2.LinkedBlockingQueue  有界阻塞链表
         *                3.SynchronousQueue     不存储元素的阻塞队列
         *                4.PriorityBlockingQueue 优先级无限阻塞队列
         *                5.DelayQueue            延时
         * threadFactory 创建线程的工厂
         * handler       饱和策略
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 8, 1000, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(), new MyThreadFactory(), new AbortPolicy());
        
        //提交任务有两种形式 
        //第一种无返回值的
        executor.execute(new Runnable() {
            
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        
        //第二种有返回值的
        Future<String>  future =  executor.submit(new Callable<String>() {

            public String call() throws Exception {
                return "返回值";
            }
        });
        
        //阻塞等待返回值
        future.get();
        
        //关闭线程池
        executor.shutdown();
    }

}
