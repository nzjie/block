package com.block.thread.core;

/**
 * 线程的suspend方法是暂停线程，resume恢复，高版本jdk已废弃<br>
 * 线程暂停，不释放资源，且数据不同步，慎用
 * 
 * @author ajie
 *
 */
public class Suspend extends Thread {

	@Override
	public void run() {
		int i = 0;
		if (Thread.currentThread().getName().equals("a"))
			System.out.println("进入了线程a,线程a准备暂停");
		while (true) {
			System.out.println(i++);
		}
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {

		Suspend t = new Suspend();
		t.setName("a");
		t.start();
		Thread.sleep(1000);
		t.suspend();
		System.out.println("这里不会打印，因为在调用了println方法时被暂停了，在暂停后，println方法并不会被释放");

	}
}
