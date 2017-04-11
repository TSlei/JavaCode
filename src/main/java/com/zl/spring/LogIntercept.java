package com.zl.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/** * ͨ��aop���غ�ִ�о������ * @author zhanglei * */
@Aspect
@Component
public class LogIntercept {
	// com.oss�������⹫���ģ�public����������������������㣺public��ʾ����������Ȩ�ޣ���һ��*��ʾ����ֵ��com.oss��ʾ������..��ʾ�Ӱ����ڶ���*��ʾ�࣬������*��ʾ�������ƣ�(..)��ʾ����
	@Pointcut(value = "execution(public * com.oss..*.*(..))")
	public void writeLog() {
		//�˷���ֻ����Ϊһ���е������,���ᱻ����ִ�С�
	}

	// ǰ�����أ���ִ��Ŀ�귽��֮ǰ�Ĳ���
	@Before("writeLog()")
	public void before() {
		this.printLog("@Before ����ִ��ǰ����������������־");
	}

	// �������أ���ִ��Ŀ�귽����ǰ��Ĳ���
	@Around("writeLog()")
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		this.printLog("@Around ����ִ��ǰ����������������־");
		Object object = pjp.proceed();
		System.out.println("����" + object + "��ӵ����档");
		this.printLog("@Around ����ִ�к󡪡�������������־");
	}

	// �������أ���ִ��Ŀ�귽��֮ǰ�Ĳ���
	@After("writeLog()")
	public void after() {
		this.printLog("@After ����ִ�к󡪡�������������־");
	}

	private void printLog(String str) {
		System.out.println(str);
	}
}
