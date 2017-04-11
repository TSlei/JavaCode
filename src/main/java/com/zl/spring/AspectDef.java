package com.zl.spring;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect 
public class AspectDef {
	
	@Pointcut("args(String)")
	public void pointcut1() {
	}

	@Before(value = "pointcut1()")
	public void beforeAdvice() {
		System.out.println("pointcut1 @Before...");
	}
}
