package com.zl.enhance.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zl.enhance.enums.EnumTest;




/*
 * 1.注解类                                    2.应用了"注解类"的类		3.对"应用了注解类的类"进行反射操作的类
 * @interface A		@A						Class C{
 * { }				Class B{}				B.class.isAnnotationPresent(A.class);
 * 											A a = B.class.getAnnotation(A.class);
 * 											}
 * */

@Retention(RetentionPolicy.RUNTIME)		//保留时期，一直保留到运行时
@Target({ElementType.METHOD,ElementType.TYPE})  //运用范围，方法上和类上   ，Class实现了Type接口
public @interface ItAnnotation {
		String color() default "blue";
		String value();
		int[] arrayAttr() default {2,3,4};
		EnumTest.TrafficLamp lamp()  default EnumTest.TrafficLamp.RED;
		MetaAnnotation annotationAttr() default @MetaAnnotation("lhm");
}
