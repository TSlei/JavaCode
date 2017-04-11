package com.zl.design.factory.generalfac;

public class MailSender implements Sender{

	@Override
	public void send() {
		System.out.println("this is a mailsender!");
	}
}
