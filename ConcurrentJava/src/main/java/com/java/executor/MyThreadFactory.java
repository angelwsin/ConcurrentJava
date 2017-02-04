package com.java.executor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory{
    
     private  AtomicInteger  count = new AtomicInteger(0);

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName(count.getAndIncrement()+"Thread");
        return t;
    }

}
