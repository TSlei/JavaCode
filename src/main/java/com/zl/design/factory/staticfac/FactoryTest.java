package com.zl.design.factory.staticfac;

import com.zl.design.factory.generalfac.Sender;

public class FactoryTest {

	public static void main(String[] args) {	
		Sender sender = SendFactory.produceMail();
		sender.send();
	}
}
