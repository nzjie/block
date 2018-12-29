package com.block.thread.core;

public class StopThread implements Runnable {

	public void run() {
		for (int i = 0; i < 100000; i++) {
			System.out.println(i + 1);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		StopThread stopThread = new StopThread();
		Thread t = new Thread(stopThread);
		t.start();
		Thread.sleep(10);
		// t.stop();//执行到中途被中断
		t.interrupt(); // 一直执行完毕，打印到100000

	}
}
