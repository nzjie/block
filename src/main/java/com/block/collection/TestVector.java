package com.block.collection;

import java.util.Vector;

/**
 * Vector 只是对方法加了锁 所以同一时刻 只有一个线程可以进入加锁的方法 但是可以有不同进程进入不同的方法
 * 
 * 当一个线程在读的时候 另一个线程完全可以对vector进行修改
 * 
 * 修正 @see TestListThread 这里的一个线程可以修改 另一个线程可以读 完全是因为在一个线程读完或写完时 已经释放了锁
 * 此时另一个线程可以竞争锁
 * 
 * @author niezhenjie
 * 
 */
public class TestVector {

	public static void main(String... args) {
		final Vector<String> vec = new Vector<String>();
		vec.add("h");
		vec.add("e");
		vec.add("l");
		vec.add("l");

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					vec.add(i + "");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < vec.size(); j++) {
				System.out.println(vec.get(j));
			}
		}
	}

}
