package com.zl.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 
 *	 	boolean	tryAcquire(int permits, long timeout, TimeUnit unit) 
	            如果在给定的等待时间内此信号量有可用的所有许可，并且当前线程未被中断，则从此信号量获取给定数目的许可。
	    boolean	tryAcquire(long timeout, TimeUnit unit) 
	            如果在给定的等待时间内，此信号量有可用的许可并且当前线程未被中断，则从此信号量获取一个许可。
  		void	release() 
                        释放一个许可，将其返回给信号量。
        void	acquire() 
                        从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，或者线程被中断。
 * 
 * */
public class TestSemaphore {

	public static void main(String[] args) {

		// 线程池
		ExecutorService exec = Executors.newCachedThreadPool();

		// 只能5个线程同时访问
		final Semaphore semp = new Semaphore(5);

		// 模拟20个客户端访问
		for (int index = 0; index < 20; index++) {

			final int NO = index;
			
			Runnable run = new Runnable() {
				public void run() {
					try {
						
						// 获取许可
						semp.acquire();
						System.out.println("Accessing: " + NO);
						Thread.sleep((long) (Math.random() * 10000));

						// 访问完后，释放
						semp.release();
						
						System.out.println("-----------------" + semp.availablePermits());
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			
			exec.execute(run);
		}
		
		// 退出线程池
		exec.shutdown();
	}
}