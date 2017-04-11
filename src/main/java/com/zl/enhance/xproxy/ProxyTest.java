package com.zl.enhance.xproxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;


/*
 * 	getMethods();方法只能获得除了构造器之外的所有方法。要获取构造方法需要使用 getConstructors();这个方法。
 *
 *	把日志功能和系统功能封装成对象，这就是面向切面编程：把切面的代码以对象的形式进行封装，然后以对象的形式传递给你，然后你执行对象的方法，就等于执行了切面的代码。
 * */
public class ProxyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		Class clazzProxy1=Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
		System.out.println("代理类名称为：   "+clazzProxy1.getName());
		
		System.out.println("----------begin constructors list");
		//$Proxy0() 
		//$Proxy(InvocationHandler,int)
		//打印构造方法
		//只有一个有参的构造方法
		Constructor[] constructors=clazzProxy1.getConstructors();
		for(Constructor constructor:constructors){
			String name=constructor.getName();
			StringBuilder sBuilder=new StringBuilder(name);
			sBuilder.append('(');
			Class[] classParams=constructor.getParameterTypes();
			for(Class param:classParams){
				sBuilder.append(param.getName()).append(',');
			}
			if(classParams!=null && classParams.length!=0)
				sBuilder.deleteCharAt(sBuilder.length()-1);

			sBuilder.append(')');
			System.out.println(sBuilder.toString());
		}
		
		
		System.out.println("---------------begin method list");
		//打印方法
		Method[] methods=clazzProxy1.getMethods();
		for(Method method:methods){
			String name=method.getName();
			StringBuilder sBuilder=new StringBuilder(name);
			sBuilder.append('(');
			Class[] classParams=method.getParameterTypes();
			for(Class param:classParams){
				sBuilder.append(param.getName()).append(',');
			}
			if(classParams!=null && classParams.length!=0)
				sBuilder.deleteCharAt(sBuilder.length()-1);

			sBuilder.append(')');
			System.out.println(sBuilder.toString());
		}
		
		
		System.out.println("---------------begin create instance object");
		//clazzProxy1.newInstance();  //没有不带参数的构造方法，所以不能用这个方法创建实例对象
		Constructor constructor=clazzProxy1.getConstructor(InvocationHandler.class);  //得到构造器，并且指定构造器传入的参数类型
		class MyInvocationHandler implements InvocationHandler{

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				// TODO Auto-generated method stub
				return null;
			}
			
		}
		//方法1
		Collection proxy1=(Collection)constructor.newInstance(new MyInvocationHandler());
		System.out.println(proxy1.toString());
		proxy1.clear();
		//proxy1.size();
		
		//方法2
		Collection proxy2=(Collection)constructor.newInstance(new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				return null;
			}
			
		});
		
		//方法里面的内部类要访问局部变量必须给变量加final
		final ArrayList target=new ArrayList();
		//方法三，将上面2种合二为一，需要三个参数，类加载器,接口，handle类，可变参数只能位于最后，所以第二个参数只能用数组
		Collection proxy3 =(Collection) getProxy(target,new MyAdvice());
		//没调用一次add方法就会执行一次invoke方法
		proxy3.add("zxx");  //代理类去调用ArrayList的add方法去实现接口
		proxy3.add("lhm");
		proxy3.add("bxd");
		System.out.println(proxy3.size());
		System.out.println(proxy3.getClass().getName());
	}

	private static Object getProxy(final Object target,final Advice advice) {
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


/*
 *
 * 
 */