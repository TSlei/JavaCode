package com.zl.enhance.aopframework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.zl.enhance.xproxy.Advice;


public class BeanFactory {
	Properties props = new Properties();

	public BeanFactory(InputStream ips) {
		try {
			props.load(ips);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object getBean(String name) {
		String className = props.getProperty(name);
		Object bean = null;
		try {
			Class clazz = Class.forName(className);
			bean = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//instanceof用法：对象  instanceof class,判断某个对象是否是这个类的实例
		if (bean instanceof ProxyFactoryBean) {
			Object proxy =null;
			ProxyFactoryBean proxyFactoryBean=(ProxyFactoryBean)bean;
			try {
				Advice advice = (Advice)Class.forName(props.getProperty(name+".advice")).newInstance();
				Object target=Class.forName(props.getProperty(name+".target")).newInstance();
				proxyFactoryBean.setAdvice(advice);
				proxyFactoryBean.setTarget(target);
				// 创建代理对象
				proxy =proxyFactoryBean.getProxy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return proxy;
		}
		return bean;
	}
}
