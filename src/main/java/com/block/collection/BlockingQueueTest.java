package com.block.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author niezhenjie
 * 
 */
public class BlockingQueueTest {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10); //底层使用数组实现LinkedBloingQueue底层使用链表实现
		//增加
		queue.add("a");  //往队列添加元素 如果队列已满 则会抛出异常
		queue.put("b"); //如果队列已满 则会一直阻塞等待队列直到队列有可用空间 然后插入(与offer对应)
		queue.offer("c"); //若果队列已满 直接返回false 队列未满 则直接插入并返回true;（与pool对应 用得比较频繁）
		for (String string : queue) {
			System.out.println(string);
		}
		//删
		System.out.println("============删==================");
		queue.peek();  //直接取出队头的元素 并不删除；
		queue.element(); //对peek封装 去取出头元素 并不删除 如果不存在 则抛出异常
		String s = queue.remove(); //删除头元素并返回 空抛出异常
		System.out.println("删除了的元素："+s);
		String s2  = queue.poll(); //取出病删除队列头元素 当队列为空 返回null（与offer对应 用得比较频繁）
		String take = queue.take(); //取出病删除队头元素 ，当队列为空 则会一直等待直到队列有新元素可以取出（与put相对应）
		System.out.println("删除了的元素："+s2);
		System.out.println("删除了的元素："+take);
		for (String string : queue) {
			System.out.println(string);
		}
		queue.take(); //队列已经为空 所以会一直阻塞在这里
	}

}
