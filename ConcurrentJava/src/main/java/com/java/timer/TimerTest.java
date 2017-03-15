package com.java.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    
    //Timer  Timer调度器
    //TimerTask 任务
    public static void main(String[] args) {
        Timer  timer = new Timer("timer", true);
        timer.schedule(new TimerTask() {
            
            @Override
            public void run() {
                System.out.println("我执行了");
            }
        }, 1000);
        
       for(;;){
          
       }
    }

}
