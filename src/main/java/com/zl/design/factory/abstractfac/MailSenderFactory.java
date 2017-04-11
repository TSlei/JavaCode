package com.zl.design.factory.abstractfac;

import com.zl.design.factory.generalfac.MailSender;
import com.zl.design.factory.generalfac.Sender;

public class MailSenderFactory extends VehicleFactory{

	@Override
	public Sender create() {
		return new MailSender();
	}
	
}
