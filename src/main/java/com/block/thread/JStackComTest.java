package com.block.thread;

/**
 * 写一个死锁 然后使用jstack工具打印thread dump 分析死锁
 * 
 * 
 * jstack打印结果: Found one Java-level deadlock: =============================
 * "Thread-1": waiting to lock monitor 0x00007f0c28004e28 (object
 * 0x00000000db84c090, a java.lang.String), which is held by "Thread-0"
 * "Thread-0": waiting to lock monitor 0x00007f0c280062c8 (object
 * 0x00000000db84c0c8, a java.lang.String), which is held by "Thread-1"
 * 
 * Java stack information for the threads listed above:
 * =================================================== "Thread-1": at
 * Lock2.run(TestJstack.java:51)（死锁所在的文件第几行） - waiting to lock
 * <0x00000000db84c090> (a java.lang.String) - locked <0x00000000db84c0c8> (a
 * java.lang.String) at java.lang.Thread.run(Thread.java:745) "Thread-0": at
 * Lock1.run(TestJstack.java:34)（死锁所在的文件第几行） - waiting to lock
 * <0x00000000db84c0c8> (a java.lang.String) - locked <0x00000000db84c090> (a
 * java.lang.String) at java.lang.Thread.run(Thread.java:745)
 * 
 * Found 1 deadlock. //这里说明了有一个死锁
 * 
 * @author ajie
 *
 */
public class JStackComTest {

	public static final String lock1 = "lock1";
	public static final String lock2 = "lock2";

	public static void main(String[] args) {
		new Thread(new Lock1()).start();
		new Thread(new Lock2()).start();

	}
}

class Lock1 implements Runnable {
	@Override
	public void run() {
		while (true) {
			synchronized (JStackComTest.lock1) {
				System.out.println("Lock1 已经锁住了lock1");
				try {
					Thread.sleep(2000); // 睡一下觉，让Lock2有足够时间锁住lock2
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 再锁住lock2
				synchronized (JStackComTest.lock2) {
					System.out.println("Lock1锁住了lock2");
				}
			}
		}

	}

}

class Lock2 implements Runnable {
	@Override
	public void run() {
		while (true) {
			synchronized (JStackComTest.lock2) {
				System.out.println("Lock2 已经锁住了lock2");
				// 尝试所lock1 产生死锁
				synchronized (JStackComTest.lock1) {
					System.out.println("Lock2锁住了lock1");
				}
			}
		}

	}

}
