package com.block.thread.core;

/**
 * synchronized拥有锁重入功能，也就是在使用synchronize时，当一个线程得到一个对象锁后，再次请求该对象的其他synchronize方法/
 * 块时，是永远可以得到锁的， 并不需要等待释放，如果需要等待释放，就会造成死锁（不可重入锁），这种关系也支持在父子类继承的环境中
 * 
 * @author ajie
 *
 */
public class SynchronizedTest {

	public synchronized void service1() {
		System.out.println("service1");
		service2(); // 不需要等到service1释放锁，可以直接拿到锁，然后进入service2
	}

	public synchronized void service2() {
		System.out.println("service2");
		service3();
	}

	public synchronized void service3() {
		System.out.println("service3");
	}

	public static void main(String[] args) {
		final SynchronizedTest test = new SynchronizedTest();
		new Thread(new Runnable() {
			public void run() {
				test.service1();
			}
		}).start();
	}
}
