package com.java.lock;

public class Data {
    
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    public void addCount(int count){
          this.count+=count;
          try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
           // logger.error("", e);
        }
    }
    

}
