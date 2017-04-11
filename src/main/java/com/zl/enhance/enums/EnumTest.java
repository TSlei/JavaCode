package com.zl.enhance.enums;

import java.util.Date;

/*	枚举就相当于一个类，其中也可以定义构造方法，成员变量，普通方法和抽象方法
 * 
 * 	枚举元素必须位于枚举体中的最开始部分，放到后面编译器会报错，枚举元素列表后面要有分号与其他成员分隔。
 * 
 * 	带构造方法的枚举
 * 		1.构造方法必须定义成私有的
 * 		2.如果有多个构造方法则通过SUM(2),MON()来区分调用哪个方法，MON()和MON一样
 * 
 * 	带方法的枚举
 * 		
 *  枚举只有一个成员时，就可以作为一种单例的实现方式。
 * 
 * */

public class EnumTest {
	public static void main(String[] args) {
		//只要用到了类，它的静态变量都会被执行，每执行一个静态变量都会调用一次构造方法
		//System.out.println(Weekday.valueOf("SUM"));
		
		//new子类的实例对象并且调用父类有参的构造方法
		new Date(300){};
		System.out.println(TrafficLamp.valueOf("GREEN"));
	}
	
	public enum Weekday{
		//通过在后面加个括号传参可以指定调用哪个构造方法，只加括号不传参表示调用无参构造方法
		SUM(2),MON(),TUE,WED,THU,FRI,SAT;   //分号可有可无，后面加构造方法时必须有

		private Weekday(){	 System.out.println("first");		}
		private Weekday(int day){	 System.out.println("second");}
	}
	
	public enum TrafficLamp{
		//枚举中的元素相当于是该枚举类的子类，因此子类需要实现父类的抽象方法。
		RED(30){

			@Override
			public TrafficLamp nextLamp() {
				return GREEN;
			}
			
		},GREEN(45){

			@Override
			public TrafficLamp nextLamp() {
				return YELLOW;
			}
			
		},YELLOW(5){

			@Override
			public TrafficLamp nextLamp() {
				return RED;
			}
			
		};
		
		public abstract TrafficLamp nextLamp();
		private int time;
		private TrafficLamp(int time){	this.time=time;
		System.out.println(this.time);}
	}
}
