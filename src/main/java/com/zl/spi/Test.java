package com.zl.spi;

import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * SPI ，全称为 Service Provider Interface，是一种服务发现机制。
 * 它通过在ClassPath路径下的META-INF/services文件夹查找文件，自动加载文件里所定义的类。
 *
 * 这一机制为很多框架扩展提供了可能，比如在Dubbo、JDBC中都使用到了SPI机制。
 *
 */
public class Test {
    public static void main(String[] args) {
        Iterator<SPIService> providers = Service.providers(SPIService.class);
        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);

        while(providers.hasNext()) {
            SPIService ser = providers.next();
            ser.execute();
        }
        System.out.println("--------------------------------");
        Iterator<SPIService> iterator = load.iterator();
        while(iterator.hasNext()) {
            SPIService ser = iterator.next();
            ser.execute();
        }
    }
}