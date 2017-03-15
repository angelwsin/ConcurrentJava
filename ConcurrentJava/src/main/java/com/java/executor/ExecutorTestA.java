package com.java.executor;

import java.util.concurrent.ArrayBlockingQueue;



public class ExecutorTestA {
    
    private  static ArrayBlockingQueue<PutTask> tasks = new ArrayBlockingQueue<ExecutorTestA.PutTask>(Integer.MAX_VALUE);
    //Executor  总执行器接口
    //ExecutorService  扩展执行器
    //AbstractExecutorService 抽象执行器
    //ThreadPoolExecutor      线程池执行器
    //ScheduledThreadPoolExecutor  调到执行器 类似Timer
    
    public static void main(String[] args) {
        
        for(;;){
          PutTask task=  tasks.poll();
          if(task!=null){
              
          }
        }
    }
    
    
    
    static class PutTask extends Thread{
        
        private String data;
        
        public PutTask(String data) {
            this.data = data;
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            System.out.println(data);
        }
    }

}
