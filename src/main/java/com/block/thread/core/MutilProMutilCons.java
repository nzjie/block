package com.block.thread.core;

/**
 * 多生产着多消费者有可能假死现象
 * 
 * 导致这样的现象是因为线程唤醒不保证是异类，有可能是生产者唤醒生产者，消费者唤醒消费者，积累着越来越多，所有的线程就进入了等待状态了
 * 
 * 解决方法是使用notifyAll
 * @author ajie
 *
 */
public class MutilProMutilCons {

	public static String value = "";

	public void setVal(Object lock) throws InterruptedException {
		synchronized (lock) {
			//这里需要用while 否则线程被唤醒可能是有生产者唤醒，如果不再次判断状态，有可能value是""就执行下面的操作了
			while (!"".equals(value)) { 
				lock.wait();
			}
			value = "value";
			System.out.println(Thread.currentThread().getName() + "设置了值");
			lock.notify();
			//lock.notifyAll();解决方法
		}
	}

	public void getVal(Object lock) throws InterruptedException {
		synchronized (lock) {
			while ("".equals(value)) {
				lock.wait();
			}
			System.out.println(Thread.currentThread().getName() + "读取");
			value = "";
			lock.notify();
			//lock.notifyAll();解决方法
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final MutilProMutilCons mpm = new MutilProMutilCons();
		final Object lock = new Object();
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Runnable() {

				public void run() {
					try {
						mpm.setVal(lock);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			t.setName("生产者-" + (i + 1));

			t.start();
		}

		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Runnable() {

				public void run() {
					try {
						mpm.getVal(lock);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			t.setName("消费者-" + (i + 1));
			t.start();
		}

		Thread.sleep(5000);
		Thread[] threads = new Thread[Thread.activeCount()];
		Thread.currentThread().getThreadGroup().enumerate(threads);
		for (Thread t : threads) {
			System.out.println(t.getName() + " 状态：" + t.getState());
		}
	}
}
