package com.zl.enhance.aopframework;

import java.io.InputStream;
import java.util.Collection;

public class AopFremeworkTest {
	public static void main(String[] args) {
		InputStream ips=AopFremeworkTest.class.getResourceAsStream("config.properties");
		Object bean=new BeanFactory(ips).getBean("xxx");
		System.out.println(bean.getClass().getName());
		((Collection)bean).clear();
	}
}
