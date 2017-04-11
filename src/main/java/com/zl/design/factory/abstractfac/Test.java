package com.zl.design.factory.abstractfac;

import com.zl.design.factory.generalfac.Sender;

public class Test {
	public static void main(String[] args) {
		VehicleFactory factory=new SmsSenderFactory();
		Sender sender=factory.create();
		sender.send();
	}
}
