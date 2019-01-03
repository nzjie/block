package com.block.thread.core.communicate;

/**
 * 唤醒多个 对比 WaitNotify，
 * 
 * 
 * 
 * @author ajie
 *
 */
public class WaitNotifyAll {

	public static void method(Object lock) throws InterruptedException {
		synchronized (lock) {
			lock.wait();
			System.out.println(Thread.currentThread().getName() + " 被唤醒");
		}
	}

	public static void notifyWait(Object lock) {
		synchronized (lock) {
			lock.notifyAll();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final Object lock = new Object();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				public void run() {
					try {
						method(lock);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		Thread.sleep(1000);
		new Thread(new Runnable() {
			public void run() {
				notifyWait(lock);
			}
		}).start();

	}

}
