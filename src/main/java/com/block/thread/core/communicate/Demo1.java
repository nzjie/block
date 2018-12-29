package com.block.thread.core.communicate;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程需要不停的轮询对象，判断属性是否改变了，如果轮询的时间很短，那么会非常消耗cpu(死循环轮询),如果很长，那么拿到的值不一定是对的（不实时），
 * 
 * 
 * @author ajie
 *
 */
public class Demo1 {

	volatile List<Integer> list = new ArrayList<Integer>();

	public void add(int val) {
		list.add(val);
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {

		final Demo1 demo = new Demo1();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					demo.add(i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				while (true) { // 死循环轮询
					if (5 == demo.size()) {
						System.out.println("==5，退出线程");
						break;
					}
				}
			}
		}).start();

	}
}
