package com.zl.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentClass {

	// 同步工具类
	private static CyclicBarrier barrier = new CyclicBarrier(2);

	private static CyclicBarrier barrier2 = new CyclicBarrier(2, new A());

	private static CountDownLatch latch = new CountDownLatch(1);

	// 只能5个线程同时访问
	private static Semaphore semaphore = new Semaphore(5);

	private static Exchanger exchanger = new Exchanger();

	// 并发容器类
	private static ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);

//	private static LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();

	private static SynchronousQueue synchronousQueue = new SynchronousQueue();

	private static DelayQueue delayQueue = new DelayQueue();

	// 锁
	private static ReentrantLock lock = new ReentrantLock();

	private static Condition condition = lock.newCondition();

	private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

	public static void main(String[] args) {
		// CyclicBarrierTest();
		// CyclicBarrierTest2();
//		CountDownLatchTest();
		SemaphoreTest();
	}

	/**
	 * CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。
	 * 它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，
	 * 屏障才会开门，所有被屏障拦截的线程才会继续干活。CyclicBarrier默认的构造方法是CyclicBarrier(int parties)，
	 * 其参数表示屏障拦截的线程数量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。
	 * 
	 * 如果把new CyclicBarrier(2)修改成new
	 * CyclicBarrier(3)则主线程和子线程会永远等待，因为没有第三个线程执行await方法。
	 */
	public static void CyclicBarrierTest() {
		new Thread(new Runnable() {
			public void run() {
				try {
					barrier.await();
				} catch (Exception e) {
				}
				System.out.println(1);
			}
		}).start();
		try {
			barrier.await();
		} catch (Exception e) {
		}
		System.out.println(2);
	}

	/**
	 * CyclicBarrier还提供一个更高级的构造函数CyclicBarrier(int parties, Runnable
	 * barrierAction)，用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景。
	 * 
	 */
	public static void CyclicBarrierTest2() {
		new Thread(new Runnable() {
			public void run() {
				try {
					barrier2.await();
				} catch (Exception e) {
				}
				System.out.println(1);
			}
		}).start();
		try {
			barrier2.await();
		} catch (Exception e) {
		}
		System.out.println(2);
	}

	public static void JoinTest() {
		Thread parser1 = new Thread(new Runnable() {
			public void run() {
				System.out.println("parser1 finish");
			}
		});
		Thread parser2 = new Thread(new Runnable() {
			public void run() {
				System.out.println("parser2 finish");
			}
		});
		parser1.start();
		parser2.start();
		try {
			// 可能parser1先执行完或者可能parser2先执行完
			// join用于让当前执行线程等待join线程执行结束。其实现原理是不停检查join线程是否存活，如果join线程存活则让当前线程永远wait，wait(0)表示永远等待下去。
			parser1.join();
			parser2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("all parser finish");

	}

	/**
	 * CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果你想等待N个点完成，这里就传入N。
	 * 
	 * 当我们调用一次CountDownLatch的countDown方法时，N就会减1，CountDownLatch的await会阻塞当前线程，
	 * 直到N变成零。由于countDown方法可以用在任何地方，所以这里说的N个点，可以是N个线程，也可以是1个线程里的N个执行步骤。
	 * 用在多个线程时，你只需要把这个CountDownLatch的引用传递到线程里。
	 * 
	 * 注意：计数器必须大于等于0，只是等于0时候，计数器就是零，调用await方法时不会阻塞当前线程。
	 */
	public static void CountDownLatchTest() {
		new Thread(new Runnable() {
			public void run() {
				System.out.println(1);
				latch.countDown();
				System.out.println(2);
				latch.countDown();
			}
		}).start();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("3");
	}

	static class A implements Runnable {
		public void run() {
			System.out.println(3);
		}

	}

	/**
	 * boolean tryAcquire(int permits, long timeout, TimeUnit unit)
	 * 如果在给定的等待时间内此信号量有可用的所有许可，并且当前线程未被中断，则从此信号量获取给定数目的许可。 boolean
	 * tryAcquire(long timeout, TimeUnit unit)
	 * 如果在给定的等待时间内，此信号量有可用的许可并且当前线程未被中断，则从此信号量获取一个许可。 void release()
	 * 释放一个许可，将其返回给信号量。 void acquire() 从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，或者线程被中断。
	 * 
	 */
	public static void SemaphoreTest() {
		// 线程池
		ExecutorService exec = Executors.newCachedThreadPool();

		// 模拟20个客户端访问
		for (int index = 0; index < 20; index++) {

			final int NO = index;
			Runnable run = new Runnable() {
				public void run() {
					try {
						// 获取许可
						semaphore.acquire();
						System.out.println("Accessing: " + NO);
						Thread.sleep((long) (Math.random() * 10000));

						// 访问完后，释放
						semaphore.release();
						System.out.println("-----------------" + semaphore.availablePermits());
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
	
	
	public static void ExchangerTest(){
		
	}

}
