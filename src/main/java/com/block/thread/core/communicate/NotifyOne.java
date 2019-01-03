package com.block.thread.core.communicate;

/**
 * notify每次只随机唤醒其中一个
 * 
 * @author ajie
 *
 */
public class NotifyOne {

	public static void method(Object lock) throws InterruptedException {
		synchronized (lock) {
			lock.wait();
			System.out.println(Thread.currentThread().getName() + " 被唤醒");
		}
	}

	public static void notifyWait(Object lock) {
		synchronized (lock) {
			lock.notify();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final Object lock = new Object();
		for (int i = 0; i < 3; i++) {
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
				notifyWait(lock);
				notifyWait(lock);
				notifyWait(lock);// 多次调用能唤醒全部，但是调用次数一定要大于等于wait的个数
			}
		}).start();

	}
}
