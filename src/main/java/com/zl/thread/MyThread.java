package com.zl.thread;

public class MyThread extends Thread {

	private int i;

	// 重写run方法，run方法的方法体就是线程执行体
	public void run() {
		for (; i < 5; i++) {
			System.out.println(this.getName() + " " + i); // 当前线程对象的线程名
			System.out.println(Thread.currentThread().getName() + " " + i); // 执行run()方法的线程名
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			// 调用Thead的currentThread方法获取当前线程
			System.out.println(Thread.currentThread().getName() + " " + i);
			if (i == 2) {
				MyThread ts = new MyThread();
				Thread thread = new Thread(ts, "thread1");
				thread.start();
			}
		}
		System.out.println(Thread.currentThread().getName());
	}
}