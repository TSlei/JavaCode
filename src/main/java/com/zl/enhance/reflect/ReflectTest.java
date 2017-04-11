package com.zl.enhance.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ReflectTest {
	public static void main(String[] args) throws Exception {
		String str1="abc";
		ReflectPoint rp=new ReflectPoint(3, 5);
		
		//这里得到的是这个类字节码上面的字段，而不是对象上的变量，可能有很多个对象rp1,rp2,所以还不知道具体取哪个对象的y
		Field fieldY=rp.getClass().getField("y");
		//这里精确到取rp对象上的字段y
		System.out.println(fieldY.get(rp));
		//这里的x是私有的，用getField("x")取不到，会报错，要使用getDeclaredFiedl();但是fieldX.get(rp)也会取不到值
		//Field fieldX=rp.getClass().getField("x");
		//不管私有还是公有都能取到x
		Field fieldX=rp.getClass().getDeclaredField("x");
		//为了可以拿到fieldX中的值，所以设置为true。
		fieldX.setAccessible(true);
		System.out.println(fieldX.get(rp));
		
		changeStringValue(rp);
		System.out.println(rp);
		
		//str1.charAt(index);
		Method method=String.class.getMethod("charAt", int.class);
		System.out.println(method.invoke(str1, 1));  //调用哪个对象的charAt方法，需要的参数
		System.out.println(method.invoke(str1, new Object[]{2}));  //通过数组来给定一个参数
		
	//	TestArguments.main(new String[]{"111","222","333"});
		String startClassName=args[0];
		Method mainMethod=Class.forName(startClassName).getMethod("main", String[].class);
		mainMethod.invoke(null, new Object[]{new String[]{"111","222","333"}}); //反射会把第二个参数拆开成多个参数，而不是当成一个数组
		
		printArray("abc");
		String[] array="a,b,c".split(",");
		printArray(array);
	}

	private static void changeStringValue(Object obj) throws Exception {
		Field[] fields=obj.getClass().getFields();
		for(Field f:fields){
			//比较字节码都是用等号，因为只有一份，比较对象的值用equals，因为有多份
			if(f.getType()==String.class){
				String oldValue=(String)f.get(obj);
				String newValue=oldValue.replace('b', 'a');
				f.set(obj, newValue);
			}
		}
	}
	
	private static void printArray(Object obj){
		Class clazz=obj.getClass();
		if(clazz.isArray()){
			int len=Array.getLength(obj);
			for(int i=0;i<len;i++){
				System.out.println(Array.get(obj, i));
			}
			
		}else{
			System.out.println(obj);
		}
	}
}

class TestArguments{
	public static void main(String[] args) {
		for(String arg:args){
			System.out.println(arg);
		}
	}
}
