package com.java.fork.join;


//Fork/Join并发执行任务框架 把大任务分割成小任务最终汇总每个小任务的结果得到大任务的结果
//工作窃取算法 指从其他队列中窃取任务来执行   每个线程都对应一个任务队列 当自己的任务队列执行完了可以窃取其他线程的任务执行
// ForkJoinTask  子类
// 1.有返回值的 RecursiveTask 2.无返回值的RecursiveAction
public class ForkJoinTest {
    
    
    
    
    
    //事例 1+2+3+4
    
   // static class SumTask  extends RecursiveTask<Int>

}
