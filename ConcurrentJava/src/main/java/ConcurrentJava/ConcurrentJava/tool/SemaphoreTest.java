package ConcurrentJava.ConcurrentJava.tool;

import java.util.concurrent.Semaphore;


//信号量 Semaphore  控制同时访问特定资源的线程数量，协调各个线程保证合理使用公共资源
public class SemaphoreTest {
    
      
    
    
    
    public static void main(String[] args) {
        
        Semaphore  sem = new Semaphore(10);
        for(int i=0;i<100;i++){
            Thread t = new CyclicBarrierThread(sem);
            t.start();
        }
        
    }
    
    
static class  CyclicBarrierThread extends Thread{
        
        private Semaphore  sem;
        public CyclicBarrierThread(Semaphore  sem) {
            this.sem = sem;
        }
        
        @Override
        public void run() {
            try {
                sem.acquire();
            } catch (InterruptedException e1) {
               e1.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"得到资源");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            
            sem.release();
            
           
        }
    }

}
