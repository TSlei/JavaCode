package com.zl.enhance.javabean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.zl.enhance.reflect.ReflectPoint;


public class IntroSpectorTest {
	public static void main(String[] args) throws Exception {
		ReflectPoint rPoint=new ReflectPoint(3, 5);
		
		String propertyName="x";
		//"x" --> "X" --> "getX" -->MethodGetX -->
		PropertyDescriptor pDescriptor=new PropertyDescriptor(propertyName, rPoint.getClass());
		Method methodGetX=pDescriptor.getReadMethod();
		Object retVal=methodGetX.invoke(rPoint);  //不接受任何参数
		//System.out.println(retVal);
		
		Method methodSetX=pDescriptor.getWriteMethod();
		methodSetX.invoke(rPoint, 7);
		//System.out.println(rPoint.getX());
		//获得属性值，最原始的获取方法，有些复杂
		System.out.println(getProperty(rPoint, propertyName));
		
		//使用BeanUtils工具包操作JavaBean
		System.out.println(BeanUtils.getProperty(rPoint, "x").getClass().getName());
		BeanUtils.setProperty(rPoint, "x", "9");
		System.out.println(rPoint.getX());
		
		BeanUtils.setProperty(rPoint, "birthday.time", "111");
		System.out.println(BeanUtils.getProperty(rPoint, "birthday.time"));
		
		/*
		 //Java7 新特性	  
		Map map=(name:"zxx",age:18);
		BeanUtils.setProperty(map, "name", lhm);
		*/
		PropertyUtils.setProperty(rPoint, "x", 9);   //x的值，不会进行类型转换，必须传入Integer类型
		System.out.println(PropertyUtils.getProperty(rPoint, "x").getClass().getName());
	}
	
	private static Object getProperty(Object rp,String propertyName) throws Exception{
		BeanInfo beanInfo=Introspector.getBeanInfo(rp.getClass());
		PropertyDescriptor[] pDescriptors=beanInfo.getPropertyDescriptors();
		Object retVal=null;
		for(PropertyDescriptor pd:pDescriptors){
			if(pd.getName().equals(propertyName)){
				Method methodGetX=pd.getReadMethod();
				retVal=methodGetX.invoke(rp);
				break;
			}
		}
		
		return retVal;
	}
}
