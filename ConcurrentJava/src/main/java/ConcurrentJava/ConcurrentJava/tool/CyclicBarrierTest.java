package ConcurrentJava.ConcurrentJava.tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


//同步屏障 让一组线程达到一个屏障点时被阻塞，直到最后一个线程达到屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行  计数器可以reset进行重置
public class CyclicBarrierTest {
    
    
    
    public static void main(String[] args) {
          CyclicBarrier  bar  = new CyclicBarrier(10);
          
          for(int i=0;i<10;i++){
              Thread t = new CyclicBarrierThread(bar);
              t.start();
          }
    }
    
    
    
     //所有线程集合到同一个点
    
    
    static class  CyclicBarrierThread extends Thread{
        
        private CyclicBarrier  bar;
        public CyclicBarrierThread(CyclicBarrier  bar) {
            this.bar = bar;
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"到达集合点");
            try {
                bar.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"出发");
        }
    }

}
