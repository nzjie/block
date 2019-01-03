package com.block.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 开始以为方法上加的synchronized只是对这个方法起效 其实不然 不管是在方法上面加synchronized 还是在方法里面使用
 * synchronized（obj） 都属于对象锁(对象内置锁) 针对的都是你传进来的这个对象 所以其中一个线程进入了其中一个加锁的 方法
 * 那么其他线程即使是进入其他加锁的方法 都要等待这把对象锁 还有一种锁是类锁（synchronized（class）） 它是针对这个类的 所以对静态方法有效
 * 
 * 注意 test1和test2的方法 这两个方法其实是两把不同的锁 因为test1拿到的是这个对象的内置锁 而test2是这个类的类锁 两个是不同的锁
 * 但如果改成test3 和test4 他们又是相同的锁了 因为test指定了这个锁是类锁 而test4是静态方法 静态方法加的锁默认就是类锁
 * 
 * @author niezhenjie
 * 
 */
public class TestListThread {

	private List<String> list = new ArrayList<String>() {

		private static final long serialVersionUID = 1L;
		{
			add("h");
			add("e");
			add("l");
			add("l");
		}

	};

	/**
	 * mark1
	 */
	public synchronized void test1() {

	}

	/**
	 * mark2
	 */
	public static synchronized void test2() {

	}

	/**
	 * mark3
	 */
	public synchronized void test3() {
		synchronized (TestListThread.class) {

		}
	}

	/**
	 * mark4
	 */
	public static synchronized void test4() {

	}

	/*public void add(String s) {
		synchronized (list) {
			try {
				Thread.sleep(3000); // 睡眠不会释放锁
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add(s);
		}
	}

	public int getSize() {
		synchronized (list) {
			return list.size();
		}
	}

	public String get(int i) {
		synchronized (list) {
			return list.get(i);
		}
	}*/

	public synchronized void add(String s) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.add(s);
	}

	public synchronized String get(int i) {
		return list.get(i);
	}

	public synchronized int getSize() {
		return list.size();
	}

	public static void main(String[] args) {
		final TestListThread tt = new TestListThread();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < tt.getSize(); i++) {
					System.out.println(tt.get(i));
				}
			}
		}).start(); // 新开一个线程遍历集合

		tt.add("o"); // 主线程调用add方法
	}
}
