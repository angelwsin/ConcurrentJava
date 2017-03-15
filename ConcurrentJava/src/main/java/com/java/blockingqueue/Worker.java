package com.java.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Worker {
    
     private  static final   ArrayBlockingQueue<Object>  queue = new ArrayBlockingQueue<Object>(1);
     private  static final   Object obj = new Object();
     private  static volatile boolean   isActive = true;
     
     
     public static void  singl(){
         queue.offer(obj);
     }
     
     public static void reset(long time){
          try {
            queue.poll(time, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
           // logger.error("", e);
        }
     }
     
     static {
       Thread t =   new W();
       t.setDaemon(true);
       t.start();
     }
     
     public static void main(String[] args) throws Exception{
         //触发线程立即执行
         Worker.singl();
         Thread.sleep(TimeUnit.SECONDS.toMillis(60));
         Worker.singl();
         Thread.sleep(Integer.MAX_VALUE);
    }
     
    static  class W extends Thread {
       
         @Override
        public void run() {
            while(isActive){
              System.out.println(Thread.currentThread().getName());
              //没有任务就在这里等待
               reset(30l); 
            }
        }
         
     }
    
    
    
    
    
    
    
    
    
    

}
