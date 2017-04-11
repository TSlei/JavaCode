package com.zl.design.factory.abstractfac;

import com.zl.design.factory.generalfac.Sender;

/*
 * 如果有新生的其他工厂，抽象工厂只需要再创建一个工厂即可，不需要修改类中的信息，具有拓展性。
 * 
 * 在其他工厂中，工厂方法模式有一个问题就是，类的创建依赖工厂类，也就是说，如果想要拓展程序，如果有新生的工厂，必须对工厂类进行修改，
 * 这违背了闭包原则
 * */
public abstract class VehicleFactory {
	
	abstract Sender create();
}
