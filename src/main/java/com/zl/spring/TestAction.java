package com.zl.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class TestAction {
	
	public String query() {
		System.out.println("查询操作");
		return "数据~~~！";
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		TestAction testAction = (TestAction) ctx.getBean("testAction");
		testAction.query();
		ctx.destroy();
	}
}
