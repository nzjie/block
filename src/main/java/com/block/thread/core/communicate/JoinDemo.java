package com.block.thread.core.communicate;

import java.util.Random;

/**
 * 如果子线程需要运行的时间很长，而主线程很快就运行完了，但是我主线程需要用到子线程的数据，如果不加以控制，是不可能获得数据的，以为这两个线程时异步进行，
 * 这有点像js的异步处理（如ajax）；使用join可以满足前面的需求
 * x.join是指所属的线程（x）对象正常执行完run方法，而是当前线程z无限阻塞，瞪等待x销毁后再执行后面的代码
 * 
 * 如果多个join，则进入等待队列，其实它内部的实现是wait
 * 
 * @author ajie
 *
 */
public class JoinDemo implements Runnable {

	public void run() {
		Random ran = new Random();
		int second = ran.nextInt(10);
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(second);
	}

	public static void main(String[] args) throws InterruptedException {
		JoinDemo jd = new JoinDemo();
		Thread t = new Thread(jd);
		t.start();
		// Thread.sleep(?);
		System.out.println("我需要在t线程执行完后再执行，但是应该休眠多久呢？");
		System.out.println("答案是不确定的");
		t.join();// main线程无限期休眠，知道线程t执行完毕被销毁，才会往下执行
		/*其实上面也可以使用下面的代替（join内部实现）
		 * 有此也可见，当调用t.wait()时是当前线程进行阻塞，而不是t进行阻塞，当t被销毁，阻塞也会结束
		synchronized (t) {
			t.wait();
		}*/
		System.out.println("我是在t执行完毕之后才执行的");
	}
}
