package com.zl.design.factory.servalfac;

import com.zl.design.factory.generalfac.Sender;

public class FactoryTest {

	public static void main(String[] args) {
		SendFactory factory = new SendFactory();
		Sender sender = factory.produceMail();
		sender.send();
	}
}
