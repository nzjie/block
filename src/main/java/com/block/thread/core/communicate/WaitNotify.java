package com.block.thread.core.communicate;

import java.util.ArrayList;
import java.util.List;

/**
 * notify也是需要获得对象锁,如果有多个线程在wait，则线程规划器会随机选取一个线程，在调用notify后，wait线程并不会马上获得锁，
 * 需要notify执行完同步块代码并退出同步块后
 * ，才能获取，同理，调用notify并不能马上释放锁，执行完同步块代码才回释放，获得对象锁的wait线程在执行完同步块方法后
 * ，如果再次调用notify，那么即使现象锁是空闲的
 * ，但是其他的wait线程也不会被激活，还会继续阻塞等待，知道一个对象发出notify或notifyAll,
 * 总而言之，wait使线程停止运行，而notify使线程继续运行
 * 
 * @author ajie
 *
 */
public class WaitNotify {

	private List<Integer> list = new ArrayList<Integer>();

	public void add(int val) {
		list.add(val);
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) throws InterruptedException {
		final WaitNotify wn = new WaitNotify();

		new Thread(new Runnable() {
			public void run() {
				synchronized (wn) {
					try {
						wn.wait();
						System.out.println("size 为5是被唤醒，但是现在size的值为：" + wn.size()
								+ " 证明调用notify后线程需要退出同步块后才释放锁");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		Thread.sleep(100); // 确保上面的线程先拿到对象锁
		new Thread(new Runnable() {
			public void run() {
				synchronized (wn) {
					for (int i = 0; i < 10; i++) {
						wn.add(i);
						if (wn.size() == 5) {
							// 执行notify，线程不会立刻释放锁，而是在执行完同步块内的代码并退出同步块后才会释放锁，
							// 可以在wait线程那里打印wn.size证明此结论
							wn.notify();
						}

					}
				}
			}
		}).start();

	}
}
