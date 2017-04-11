package com.zl.design.factory.abstractfac;

import com.zl.design.factory.generalfac.Sender;
import com.zl.design.factory.generalfac.SmsSender;

public class SmsSenderFactory extends VehicleFactory{

	@Override
	public Sender create() {
		return new SmsSender();
	}

}
