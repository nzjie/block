package com.block.thread.core.communicate;

/**
 * 单一生产者、消费者
 * 
 * @author ajie
 *
 */
public class ProducerComsumer {
	public static String value = "";

	public void setValue(Object lock) throws InterruptedException {
		synchronized (lock) {
			if (!"".equals(value)) {
				lock.wait();
			}
			value = "val";
			System.out.println("set方法设置值");
			lock.notify();
		}
	}

	public void getValue(Object lock) throws InterruptedException {
		synchronized (lock) {
			if ("".equals(value)) {
				lock.wait();
			}
			System.out.println("get方法打印值：" + value);
			value = "";
			lock.notify();
		}
	}

	public static void main(String[] args) {
		final Object lock = new Object();
		new Thread(new Runnable() {
			ProducerComsumer pc = new ProducerComsumer();

			public void run() {
				try {
					while (true) {
						pc.setValue(lock);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(new Runnable() {
			ProducerComsumer pc = new ProducerComsumer();

			public void run() {
				try {
					while (true) {
						pc.getValue(lock);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}
}
