package com.java.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;



public class DefLock implements Lock{
    
    private MySync mySync  = new MySync();

    public void lock() {
        mySync.acquire(1);
    }

    public void lockInterruptibly() throws InterruptedException {
    }

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {
        mySync.release(1);
    }

    public Condition newCondition() {
        return null;
    }
    
    //继承AQS同步器提供的模板方法 完成独占锁的获得和释放，共享锁的获得和释放
    //AQS 中维护着一个双向队列对线程的同步
    //volatile int statestate 维护着锁的状态
    //private transient volatile Node head;  队头
   // private transient volatile Node tail;  队尾
    static class MySync extends AbstractQueuedSynchronizer{
        
        /**  */
        private static final long serialVersionUID = 1L;

        /**
         * 获得锁
         * 复写AQS提供的模板方法完成锁的获得
         * @see java.util.concurrent.locks.AbstractQueuedSynchronizer#Acquire(int)
         * 根据模板方法tryAcquire的返回值判断是否得到锁
         */
        @Override
        protected boolean tryAcquire(int arg) {
            Thread t = Thread.currentThread();
             int c = getState();
             if(c==0){
                 if(compareAndSetState(0, arg)){
                     setExclusiveOwnerThread(t);
                     return true;
                 }
             }else if(t==getExclusiveOwnerThread()){
                 setState(c+arg);
                 return true;
             }
            return false;
        }
        
        /**
         * 锁的释放
         * @see java.util.concurrent.locks.AbstractQueuedSynchronizer#tryRelease(int)
         */
        @Override
        protected boolean tryRelease(int arg) {
            int c = getState()-arg;
            boolean free = false;
            if(c==0){
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }
        
        
    }
    
    public static void main(String[] args) {
          final Data data = new Data();
          final DefLock lock = new DefLock();
          for(int i=0;i<100;i++){
              new Thread(){
                  public void run() {
                      lock.lock();
                      data.addCount(1);
                      System.out.println(Thread.currentThread().getName()+":"+data.getCount());
                      lock.unlock();
                  };
                  
              }.start();
          }
    }

}
