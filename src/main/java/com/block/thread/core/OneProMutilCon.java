package com.block.thread.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 一生产者多消费者，如果只用notify，可能会假死，使用notifyAll解决
 * 
 * 假设生产者进入了等待队列，消费者只有一个没有进入等待队列，执行下面的代码，唤醒一个线程，这时候如果唤醒的还是
 * 消费者，那么生产者并没有生产东西，那么唯一没有进入等待队列的线程也会以为没有东西可消费而进入等待队列，导致假死，
 * notifyAll可以唤醒所有的线程，线程在一个个竞争锁，如果前面全都是消费着抢到，没关系，因为没有东西消费而进入等待队列
 * 释放了锁，这时候生产者因为前面已经被唤醒了，只要得到锁，就能继续执行，所以生产者生产东西，又唤醒所有的消费者，消费者再竞争锁
 * 拿到的执行下面的，在唤醒全部包括生产者，如此循环，所以不会出现假死
 * 
 * 
 * @author ajie
 *
 */
public class OneProMutilCon {
	public List<Integer> stack = new ArrayList<Integer>(1);

	public void setVal(Object lock) throws InterruptedException {
		synchronized (lock) {
			// 这里需要用while 否则线程被唤醒可能是有生产者唤醒，如果不再次判断状态，有可能value是""就执行下面的操作了
			/*if*/while (stack.size() == 1) {
				lock.wait();
			}
			stack.add(1);
			System.out.println(Thread.currentThread().getName() + "设置了值");
			// lock.notify();
			lock.notifyAll();// 假死解决方法
		}
	}

	public void getVal(Object lock) throws InterruptedException {
		synchronized (lock) {
			/*if*/while (stack.size() == 0) { // if会出现下标溢出异常，因为消费者线程唤醒的可能是消费者，改用while就可以了
				lock.wait();
			}
			System.out.println(Thread.currentThread().getName() + "读取");
			stack.remove(0);
			// lock.notify();
			lock.notifyAll();// 假死解决方法
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final OneProMutilCon opm = new OneProMutilCon();
		final Object lock = new Object();
		for (int i = 0; i < 1; i++) { // 一个
			Thread t = new Thread(new Runnable() {

				public void run() {
					try {
						while (true) {
							opm.setVal(lock);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			t.setName("生产者-" + (i + 1));

			t.start();
		}

		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Runnable() {

				public void run() {
					try {
						while (true) {
							opm.getVal(lock);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			t.setName("消费者-" + (i + 1));
			t.start();
		}

		Thread.sleep(2000);
		Thread[] threads = new Thread[Thread.activeCount()];
		Thread.currentThread().getThreadGroup().enumerate(threads);
		for (Thread t : threads) {
			System.out.println(t.getName() + " 状态：" + t.getState());
		}
	}

}
