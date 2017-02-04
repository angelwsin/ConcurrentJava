package com.java.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//每一个Condition维护着一个等待队列，当调用condition.await() 会从同步队列的头节点移入到等待队列的尾部，因为只有获得锁的线程才能执行
//当调用condition.signal() 则会从等待队列中移入到同步队列的尾部
public class LockCondition {
    
    
     private ReentrantLock lock   = new ReentrantLock();
     
     private  Condition  condition = lock.newCondition();
     
     private int  count = 0;
     
     
     
     public static void main(String[] args) {
         final LockCondition c = new LockCondition();
         new Thread(){
             
             public void run() {
                c.lock.lock();
               
                try {
                    
                    while(c.count<10){
                        Thread.sleep(1000);
                        c.count++; 
                    }
                    c.condition.signal();
                } catch (InterruptedException e) {
                   e.printStackTrace();
                }finally{
                    c.lock.unlock();
                }
               
                
             };
         }.start();
         c.conditionMethod();
    }
     
     
     
     
     //三步走  获得锁   等待或释放  释放锁
     public void conditionMethod(){
           lock.lock();
           try{
               while(count<10){
                   condition.await();    
               }
               System.out.println(count);
           }catch(InterruptedException e){
             e.printStackTrace();  
           }
           finally{
             lock.unlock();  
           }
         
         
     }

}
