package com.block.thread.core;

/**
 * 守护线程，为非守护线程提供服务，如jvm的垃圾回收线程，当全部守护线程执行完毕，守护线程就算圆满结局了，可以退出终止
 * 
 * @author ajie
 *
 */
public class DeamonThread extends Thread {

	@Override
	public void run() {
		int i = 0;
		while (true) {
			System.out.println(i++);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		DeamonThread t = new DeamonThread();
		t.setDaemon(true);
		t.start();
		Thread.sleep(1000);
		System.out.println("main线程（非守护）执行完毕了，所有非守护线程都执行完了，守护线程t也没有必要在执行了，退出");

	}
}
