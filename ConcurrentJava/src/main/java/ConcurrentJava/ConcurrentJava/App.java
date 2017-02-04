package ConcurrentJava.ConcurrentJava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;


//LockSupport是用来创建锁和其他同步类的基本线程阻塞原语
public class App 
{
    
    private static List<Thread> threads = new ArrayList<Thread>();
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
      //  LockSupport.park();
       App app = new App();
       //app.park();
       
       for(int i=0;i<10;i++){
          Thread t =  new ParkThread(app);
           threads.add(t);
           t.start();
       }
       
       LockSupport.unpark(threads.get(0));
        
    }
    
    public void park(){
        LockSupport.park(this);
    }
    
    static class ParkThread extends Thread{
        private App app;
        public ParkThread(App app) {
            this.app = app;
        }
        
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            LockSupport.park(app);
            System.out.println(Thread.currentThread().getName()+"unPark");
            
        }
        
    }
}
