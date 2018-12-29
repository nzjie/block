package com.block.thread.core;

/**
 * 打印结果：i=4 i=2 i=3 i=5 i=5 <Br>
 * 虽然println是同步方法，但是i--发生在println方法之外，所以出现了线程不安全 print方法<Br>
 * prinln方法<br>
 * public void println(String x) {<br>
 * synchronized (this) {<br>
 * print(x);<br>
 * newLine();<br>
 * }<br>
 * }<br>
 * 
 * @author ajie
 *
 */
public class Print extends Thread {
	private int i = 5;

	@Override
	public void run() {
		super.run();
		System.out.println("i=" + (i--));
	}

	public static void main(String[] args) {
		Print print = new Print();
		Thread t1 = new Thread(print);
		Thread t2 = new Thread(print);
		Thread t3 = new Thread(print);
		Thread t4 = new Thread(print);
		Thread t5 = new Thread(print);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}
