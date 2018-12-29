package com.block.thread.core;

/**
 * synchronized效率问题
 * 
 * 一个线程执行一个方法时，遇到synchronized块，拿到锁，其他线程可以进入这个方法，执行到synchronized块时
 * 也是需要等待，这可以解决效率问题，如果一个方法有有一段代码需要执行很长时间，但是这段代码不会出现线程安全问题，只有一小段会出现线程安全问题，那么可以只
 * 在这一小段上加入同步synchronized（this）{}关键字，只有执行这一小段需要等到锁，其他的不需要<br>
 * 不仅在longTimeMethod方法内的同步块会阻塞，在其他方法的同步块也会阻塞，说明synchronized锁是一个"对象监听器"
 * 
 * @author ajie
 *
 */
public class SynchronizedBlock {

	public int ins;

	public void longTimeMethod(int ins) {
		System.out.println("这里需要执行很长时间，但是其他线程可以不能锁释放进入这里");
		synchronized (this) {// 执行到这里需要等待锁
			this.ins = ins;
		}
	}

	public void longTimeMethod2(int ins) {
		synchronized (this) {// 如果longTimeMethod先拿到同步锁，那么这里也会阻塞等待
			System.out.println("longTimeMethod2");
		}
	}

}
