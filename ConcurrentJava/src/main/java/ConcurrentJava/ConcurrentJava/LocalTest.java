package ConcurrentJava.ConcurrentJava;

public class LocalTest {
    
    
    private LocalThreadTest local  = LocalThreadTest.instance();
    
    public static void main(String[] args) {
        final LocalTest test = new LocalTest();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    System.out.println(test.local);    
                    
                };
                
            }.start();
        }
        
    }

}
