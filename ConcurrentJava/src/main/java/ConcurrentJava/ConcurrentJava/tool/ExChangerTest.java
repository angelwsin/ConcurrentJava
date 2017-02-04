package ConcurrentJava.ConcurrentJava.tool;

import java.util.concurrent.Exchanger;


//Exchanger 进行线程间数据的交换
public class ExChangerTest {
    
    
    
    public static void main(String[] args) {
        Exchanger<String>  exch = new Exchanger<String>();
        
        new A(exch).start();
        new B(exch).start();
        
    }
    
    
    
    
    
    
    
    
    static class  A extends Thread{
        
        private Exchanger<String>  exch;
        
        public A(Exchanger<String>  exch) {
            this.exch = exch;
        }
        @Override
        public void run() {
            
            String a = "银行流入录入A";
            try {
               String b =  exch.exchange(a);
               System.out.println(b);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }
    }
    
    static class  B extends Thread{
        private Exchanger<String>  exch;
        public B(Exchanger<String>  exch) {
            this.exch = exch;
        }
        @Override
        public void run() {
           String b ="银行流水流入B";
           try {
           String a =  exch.exchange(b);
           System.out.println(a);
           System.out.println("对比结果："+b.equals(a));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
    }

}
