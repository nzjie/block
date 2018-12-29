package com.block.thread.core;

/**
 * 接着VolatileDemo1
 * 
 * @author ajie
 *
 */
public class VolatileDemo2 {

	volatile private boolean isRunning = true;

	public void setIsRunning(boolean b) {
		isRunning = b;
	}

	public void printStringMethod() {
		while (isRunning) {
		}
		System.out.println("退出循环");
	}

	public static void main(String[] args) throws InterruptedException {
		final VolatileDemo2 demo = new VolatileDemo2();
		new Thread(new Runnable() {
			public void run() {
				demo.printStringMethod();
			}
		}).start();
		Thread.sleep(1000);
		demo.setIsRunning(false);
		System.out.println("已经将isRunning设置为false");

	}

}
