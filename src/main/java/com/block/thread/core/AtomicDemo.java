package com.block.thread.core;

/**
 * volatile并不能保证原子性，比如i++,执行这一操作，会分为三步走，从主线程中load
 * i的值到线程内存中,计算i+1,将结果赋值给i，在将i写到主内存
 * ，volatile只会保证每次执行都从主内存中把i的值load回来，后面的操作并不同步，如果在执行后面的i
 * +1和赋值时，其他线程改变了主内存i的值，方法区执行的线程并不知道i被修改，最后还会吧i写到主内存，造成数据不一致，
 * 
 * 这个demo知道就行了，很难模拟
 * 
 * @author ajie
 *
 */
public class AtomicDemo {

	volatile private int i = 0;

	public void add() {
		i++;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getI() {
		return i;
	}

	public static void main(String[] args) throws InterruptedException {
		final AtomicDemo demo = new AtomicDemo();
		for (int i = 0; i < 100000; i++) {
			new Thread(new Runnable() {
				public void run() {
					demo.add();
				}
			}).start();
		}

		for (int i = 0; i < 100000; i++) {
			new Thread(new Runnable() {
				public void run() {
					demo.setI(555);// 线程修改i
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(demo.getI());
				}
			}).start();
		}
	}
}
