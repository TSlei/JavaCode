package com.zl.design.factory.generalfac;

public class FactoryTest {
	
	public static void main(String[] args) {  
		
        SendFactory factory = new SendFactory();  
        Sender sender = factory.produce("sms");  
        sender.send();  
    }  
}
