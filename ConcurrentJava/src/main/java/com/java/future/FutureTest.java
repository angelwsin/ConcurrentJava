package com.java.future;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTest {

    
    
    public static void main(String[] args) throws Exception{
        FutureTask<String> f = new FutureTask<String>(new Callable<String>() {

            public String call() throws Exception {
                return "返回值";
            }
        });
         
        f.run();
        System.out.println(f.get());
        
        
        /**
         * Future 接口  定义 对于没有执行的任务 isDone() 可以取消cancel() 用isCancelled()判断是否取消了 
         *              可以有返回值get()
         * RunnableFuture 继承了Runnable  
         * 
         * FutureTask 实现了以上接口
         * 定义了任务的状态 state
         * NEW = 0         任务 创建初始状态
         * COMPLETING = 1  执行run() 并调用 Callable.call()方法
         * NORMAL = 2      设置结果值之后
         * EXCEPTIONAL = 3 异常状态
         * CANCELLED = 4
         * INTERRUPTING = 5
         * INTERRUPTED = 6
         * 
         * callable   被执行的task
         * outcome    调用 get()的返回值
         * runner     正在运行的thread
         * waiters    调用get()阻塞的线程队列   链表的形式排列
         */
        
       
         
    }
}
