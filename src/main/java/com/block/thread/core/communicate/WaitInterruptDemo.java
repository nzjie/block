package com.block.thread.core.communicate;

/**
 * wait遇到interrupt，会抛出waitInterruptException,见WaitInterruptDemo
 * 
 * @author ajie
 *
 */
public class WaitInterruptDemo {

	private Object obj = new Object();

	public void method() throws InterruptedException {
		synchronized (obj) {
			System.out.println("wait begin");
			// 如果在这里吧异常catch掉了 那么会往下执行 打印wai end(异常被处理了)，抛出去不会，线程已经被终止了
			obj.wait();

			System.out.println("wait end");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final WaitInterruptDemo wtd = new WaitInterruptDemo();
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					wtd.method();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		Thread.sleep(1000);
		t.interrupt(); // main线程中断t线程

	}

}
