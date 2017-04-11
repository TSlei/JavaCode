package com.zl.design.singleton.lazy;

//懒汉式单例类.在第一次调用的时候实例化自己
/*
 * Singleton通过将构造方法限定为private避免了类在外部被实例化，
 * 在同一个虚拟机范围内，Singleton的唯一实例只能通过getInstance()方法访问。
 * 
 * 事实上，通过Java反射机制是能够实例化构造方法为private的类的，那基本上会使所有的Java单例实现失效。
 * */
public class Singleton {  
    private Singleton() {}  
    private static Singleton single=null;  
    //静态工厂方法   
    public static Singleton getInstance() {  
         if (single == null) {    
             single = new Singleton();  
         }    
        return single;  
    }  
    
    
    //但是以上懒汉式单例的实现没有考虑线程安全问题，它是线程不安全的，并发环境下很可能出现多个Singleton实例，要实现线程安全，有以下三种方式，都是对getInstance这个方法改造，保证了懒汉式单例的线程安全，如果你第一次接触单例模式，对线程安全不是很了解，可以先跳过下面这三小条，去看饿汉式单例，等看完后面再回头考虑线程安全的问题：
   // 1、在getInstance方法上加同步
    public static synchronized Singleton getInstance2() {  
        if (single == null) {    
            single = new Singleton();  
        }    
       return single;  
    } 
    
    //2、双重检查锁定
    public static Singleton getInstance3() {  
        if (single == null) {    
            synchronized (Singleton.class) {    
               if (single == null) {    
                  single = new Singleton();   
               }    
            }    
        }    
        return single;   
    }
} 
