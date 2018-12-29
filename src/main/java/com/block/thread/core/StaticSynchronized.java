package com.block.thread.core;

/**
 * synchronized静态方法锁的是字节文件xxx.class，与对象实例的锁是不一样的，在静态方法里面同步块也是这样。
 * class锁会对这个类的所有实例起作用
 * 
 * @author ajie
 *
 */
public class StaticSynchronized {

	synchronized static public void lock(final String name, final int delay) {
		try {
			Thread.sleep(delay);
			System.out.println(name + "  class 锁");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	synchronized public void lock2() {
		System.out.println("对象 锁");
	}

	public static void main(String[] args) throws InterruptedException {
		final StaticSynchronized ss = new StaticSynchronized();
		new Thread(new Runnable() {
			public void run() {
				StaticSynchronized.lock("A", 3000);

			}
		}).start();
		Thread.sleep(100); // 确保上面的线程已经进入StaticSynchronized的同步块
		new Thread(new Runnable() {
			public void run() {
				StaticSynchronized.lock("B", 10);// 会同步执行lock方法，A释放后才能进入

			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				ss.lock2();
			}
		}).start();

	}

}
