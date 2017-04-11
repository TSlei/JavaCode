package com.zl.jvm;


/*java�?栈上分配
 * - 小对�?�?��几十个bytes)，在没有逃�?(每个栈都有一个自己的线程，只有自己的线程访问的时�?的情况下，可以直接分配在栈上
 * - 直接分配在栈上，可以自动回收(不需要调用GC)，减轻GC压力�?
 * - 大对象或者逃逸对象�?不只�?��线程去访问的对象)无法在栈上分配
 * 
 * */

public class OnStackTest {
	public static void alloc() {
		byte[] b = new byte[2];// 存储到堆中，堆中内存不够时，系统会主动调用GC清理堆中垃圾
		b[0] = 1;
	}

	// -server -Xmx10m -Xms10m
	// -XX:+DoEscapeAnalysis -XX:+PrintGC
	// 在Arguments里面添加上面两个参数，可以打印GC的日志信息，不同的版本的jdk虚拟机的存储不同
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			alloc();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
