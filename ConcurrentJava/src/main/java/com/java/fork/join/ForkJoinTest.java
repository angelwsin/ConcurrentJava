package com.java.fork.join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

//Fork/Join并发执行任务框架 把大任务分割成小任务最终汇总每个小任务的结果得到大任务的结果
//工作窃取算法 指从其他队列中窃取任务来执行   每个线程都对应一个任务队列 
//当自己的任务队列执行完了可以窃取其他线程的任务执行
// ForkJoinTask  子类
// 1.有返回值的 RecursiveTask 2.无返回值的RecursiveAction
//ForkJoinPool: ForkJoinTask 通过ForkJoinPool来执行
//把任务放到线程维护的双端队列，当一个线程暂时没有任务随机的从其他队列中获得
public class ForkJoinTest {
    
	  //事例 1+2+3+4
    public static void main(String[] args) throws Exception{
		ForkJoinPool pool = new ForkJoinPool();
		Future<Integer> future = pool.submit(new SumTask(1, 4));
		System.out.println(future.get());
	}
    
    
  
    
    static class SumTask  extends RecursiveTask<Integer>{
    	//阀门值
    	private static final int THRESHOLD  =2;
    	
    	private  int start;
    	private  int end;
    	
    	

		public SumTask(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected Integer compute() {
			int  c = end -start;
			int sum = 0;
			if(c<THRESHOLD){
				for(int i=start;i<=end;i++){
					 sum+=i;
				}
			}else{
				int mid = (start+end)/THRESHOLD;
				SumTask left = new SumTask(start, mid);
				SumTask right = new SumTask(mid+1, end);
				 left.fork();
				 right.fork();
				 sum +=left.join()+right.join();
			}
			return sum;
		}
    	
    }

}
