package com.zl.jvm;

public class JVMDemo {
	public static void main(String[] args) {
		add(2, 2);
	}

	// 按顺序来，变量a代表0，b代表1，c代表2
	public static int add(int a, int b) {
		int c = 0;
		c = a + b;
		return c;
	}
	// 0:iconst_0 //0压栈 (iconst应该是用于将基本类型数据压栈,const：常量
	// 1:istore_2 //弹出int，存放于局部变量2 (�?存放于c中，赋值的时候先弹出再赋值,istore代表弹出栈的意思，store:存储)
	// 2:iload_0 //把局部变量a压栈 (iload代表压入栈的意思，load：压栈
	// 3:iload_1 //把局部变量b压栈
	// 4:iadd //弹出两个变量，求和，结果压栈
	// 5:istore_2 //弹出结果，存放于�?�变量2(就是赋�?)
	// 6:iload_2 //将局部变量c压栈
	// 7:ireturn //返回栈顶元素 (return：返回)
	// 在栈中，往栈里面压数据都是放到栈顶，然后再加数据就将原来的数据往下面压，返回的时候是返回栈顶元素
}
