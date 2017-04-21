package com.java.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class UnsafeTest {
    
     int  state = 3;
   
    
    public static void main(String[] args) throws Exception{
        UnsafeTest test = new UnsafeTest();
        Class<?> clz = Class.forName("sun.misc.Unsafe");
        Field  unSafe =  clz.getDeclaredField("theUnsafe");
        unSafe.setAccessible(true);
        Unsafe u = (Unsafe) unSafe.get(null);

        Class<?> localClass = UnsafeTest.class;
        long stateOffset = u.objectFieldOffset(localClass.getDeclaredField("state"));
        u.compareAndSwapInt(test, stateOffset, 3, 1);
        System.out.println(test.state);
    }

}
