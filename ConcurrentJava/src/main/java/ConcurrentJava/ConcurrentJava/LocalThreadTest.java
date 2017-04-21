package ConcurrentJava.ConcurrentJava;

public class LocalThreadTest {
    
      private long x= System.currentTimeMillis();
      private Thread thread ;
    
    
     private static AbstractLocal<LocalThreadTest> Local = new AbstractLocal<LocalThreadTest>() {

        @Override
        public LocalThreadTest newInstance() {
            LocalThreadTest t =  new LocalThreadTest() ;
            t.thread = Thread.currentThread();
            return t;
        }
    };
    
    
    
    
    public static LocalThreadTest instance(){
        return  Local.get();
    }
    
    
    
    
    
    
    
    public Thread getThread() {
        return thread;
    }







    public long getX() {
        return x;
    }







    static abstract  class AbstractLocal<T> {
        
        private ThreadLocal<T> Cache = new ThreadLocal<T>();
        
        public  T get(){
            
             if(Cache.get()==null){
                 return newInstance();
             }
             return Cache.get();
        }
        
        public abstract T  newInstance();
    }

}
