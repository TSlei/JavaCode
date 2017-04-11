package com.zl.enhance.aopframework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.zl.enhance.xproxy.Advice;


public class ProxyFactoryBean {
	
	private Advice advice;
	private Object target;
	public Advice getAdvice() {
		return advice;
	}
	public void setAdvice(Advice advice) {
		this.advice = advice;
	}
	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}
	
	public Object getProxy(){
		Object proxy3=Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				new InvocationHandler(){  //匿名内部类
			
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
						
//						long beginTime=System.currentTimeMillis();
//						Object retValue=method.invoke(target, args);
//						long endTime=System.currentTimeMillis();
//						System.out.println(method.getName()+"()   runningTime="+(endTime-beginTime));
//						return retValue;
					//	return method.invoke(proxy, args);    //死循环
						
						
						advice.beforeMethod(method);
						Object retValue=method.invoke(target, args);
						advice.afterMethod(method);
						
						return retValue;
					}
		});
		return proxy3;
	}
}
