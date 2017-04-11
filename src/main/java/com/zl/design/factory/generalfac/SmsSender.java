package com.zl.design.factory.generalfac;

public class SmsSender implements Sender{

	@Override
	public void send() {
		System.out.println("this is a SmsSender!");
	}
	
}
