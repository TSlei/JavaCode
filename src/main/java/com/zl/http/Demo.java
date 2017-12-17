package com.zl.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Demo {
	
	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.loadProperties();
	}
	
	public void loadProperties(){
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("classMapping.properties");
		   Properties p = new Properties();
		   try{
		       p.load(inputStream);
		   } catch (IOException e1){
		    e1.printStackTrace();
		   }
		System.out.println("ip:"+p.getProperty("ip") + ",port:"+p.getProperty("port") + ",class:"+p.getProperty("Abstract"));
	}
}
