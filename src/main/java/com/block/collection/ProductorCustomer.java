package com.block.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * wait 和notify(All)一定要对竞争资源进行加锁，如果不加锁的话，则会报 IllegalMonitorStateException 异常
 * 在while循环里而不是if语句下使用wai
 * 
 * @author niezhenjie
 * 
 */
public class ProductorCustomer {

	private final int MAX_SIZE = 10;
	private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(MAX_SIZE);

	public void add(String str) {
		// 满了 不能再添加了让线程等待
		synchronized (queue) {
			while (queue.size() == MAX_SIZE) {
				System.out.println("队列已满 暂时不能添加");
				try {
					Thread.sleep(1000);
					queue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			queue.offer(str);
			System.out.println("添加了一个对象 " + str);
			// 唤醒等待该队列的所有线程（加了一个 队列不再为空了 唤醒取的线程）
			queue.notifyAll();
		}
	}

	public void get() {
		System.out.println("队列空空如也 暂时不能取对象");
		// 没有内容可取 等待吧
		synchronized (queue) {
			while (queue.isEmpty()) {
				
				try {
			//		Thread.sleep(new Random().nextInt(10)*100);
					queue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			String str = queue.poll();
			System.out.println("取得一个对象 " + str);
			// 唤醒等待该队列的线程（取了一个 队列不可能满的 唤醒加的线程)
			queue.notifyAll();
		}
	}

	/**
	 * 需要先创建一个线程 在执行main线程的for @seeCommunite
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final ProductorCustomer pc = new ProductorCustomer();

		// 开一个线程用来get
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					pc.get();
					
				}
			}
		}).start();

		// 用main线程add（要在上述创建线程之后）
		for (int i = 1; i <= 50; i++) {
			pc.add(i + "");
		}
	}
}
