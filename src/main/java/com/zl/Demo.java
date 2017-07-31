package com.zl;

/**
 * Created by Tinkpad on 2017/5/12.
 */
public class Demo {

    private static Demo demo;

    public static Demo getInstance() {
        return demo;
    }

    public Demo() {
        Demo.demo = this;
    }


    public static void main(String[] args) {
//        Demo d = new Demo();
//        System.out.println(getInstance());
    }
}

