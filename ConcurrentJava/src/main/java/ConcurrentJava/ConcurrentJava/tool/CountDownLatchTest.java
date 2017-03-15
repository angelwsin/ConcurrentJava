package ConcurrentJava.ConcurrentJava.tool;

import java.util.concurrent.CountDownLatch;


// CountDownLatch允许一个或多个线程等待其他线程完成操作    计数器只能使用一次
public class CountDownLatchTest {
    
    
    
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);
        
        for(int i=0;i<10;i++){
           Thread t = new CountDownLatchThread(latch); 
            t.start();
        }
    }
    
    
    //所有线程集合到同一个点
    
    
    static class  CountDownLatchThread extends Thread{
        
        private CountDownLatch latch;
        public CountDownLatchThread(CountDownLatch latch) {
            this.latch = latch;
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            
            System.out.println(Thread.currentThread().getName()+"到达集合点");
            latch.countDown();
            try {
                latch.await();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"出发");
        }
    }
    
    //秒杀 超卖 少卖  数据库库存乐观锁
    //订单判断  队列   缓存中用户名，时间点
    
}
