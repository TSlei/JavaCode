package com.zl.design.singleton.lazy;


//这种比上面1、2都好一些，既实现了线程安全，又避免了同步带来的性能影响。
public class SingletonForInner {
	private static class LazyHolder {    
	       private static final SingletonForInner INSTANCE = new SingletonForInner();    
	    }    
	    private SingletonForInner (){}    
	    public static final SingletonForInner getInstance() {    
	       return LazyHolder.INSTANCE;    
	    } 
}
