package com.block.thread.core.communicate;

public class WaitTime {
	public void waitTime(Object lock) throws InterruptedException {
		synchronized (lock) {
			lock.wait(1000);
			System.out.println("线程被自动唤醒");
		}

	}

	public static void main(String[] args) {
		WaitTime waitTime = new WaitTime();
		try {
			waitTime.waitTime(new Object());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
