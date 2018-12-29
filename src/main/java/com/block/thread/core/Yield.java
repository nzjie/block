package com.block.thread.core;

/**
 * yield：让出cpu的使用权给其他线程，但是时间不确定，有可能刚让出，立刻又获取cup使用权回来了
 * 
 * @author ajie
 *
 */
public class Yield extends Thread {
	@SuppressWarnings("unused")
	@Override
	public void run() {
		int count = 0;
		long curentTime = System.currentTimeMillis();
		for (int i = 0; i < 500000; i++) {
			// Thread.yield();
			count += i + 1;
		}
		long endTime = System.currentTimeMillis();
		System.out.println("花费时间：" + (endTime - curentTime) + "ms");
	}

	public static void main(String[] args) {
		Yield t = new Yield(); // 注释掉Thread.yield()花费大约3ms，打开注释，50ms（不一定）
		t.start();
	}
}
