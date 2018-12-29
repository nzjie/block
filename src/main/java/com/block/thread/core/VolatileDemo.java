package com.block.thread.core;

/**
 * volatile使变量在多线程间可见
 * 
 * 在有些jvm中运行，可能会打印 “退出循环”，即isRunning变成了false,在debug时，会打印“退出循环”，但是大多数jvm或者在
 * 运行是在jvm参数里传入-server，都不会打印这句话，原因在于jvm考虑到效率问题，在方法里使用的isRunning会保存在线程私有栈中，
 * 不会到公共堆栈里取值，所以即使isRunning被其他线程改变了，printStringMethod私有栈中的isRunning依然是没变，解决这个
 * 问题，就要用到关键字 volatile,这也等于介绍了volatile关键字的作用了，它强制线程每次都到公共堆栈中取值，而不使用线程私有栈中的值
 * 
 * 详情见 VolatileDemo2
 * 
 * @author ajie
 *
 */
public class VolatileDemo {

	private boolean isRunning = true;

	public void setIsRunning(boolean b) {
		isRunning = b;
	}

	public void printStringMethod() throws InterruptedException {
		while (isRunning) {
			// 如果休眠了，isRunning会变成false，是不是休眠了就会重新到公共堆栈里取值呢?这个有待学习
			// Thread.sleep(100);
		}
		System.out.println("退出循环");
	}
	public static void main(String[] args) throws InterruptedException {
		final VolatileDemo demo = new VolatileDemo();
		new Thread(new Runnable() {
			public void run() {
				try {
					demo.printStringMethod();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		Thread.sleep(1000);// 确保上面的线程进入了死循环
		demo.setIsRunning(false);
		System.out.println("已经将isRunning设置为false");

	}
}
