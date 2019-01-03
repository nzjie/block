package com.block.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author niezhenjie
 * 
 */
public class LockCache<V> {

	private Map<String, V> cache = new HashMap<String, V>();
	/** 读写锁 */
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private Lock r_lock = lock.readLock();
	private Lock w_lock = lock.writeLock();
	private int testdata = 0; // 用于异常测试

	public LockCache(int data) {
		testdata = data;
	}

	public void setTestData(int data) {
		testdata = data;
	}

	/**
	 * 通过缓存获取值 如果缓存没有找到 那么会到持久层查找 然后把查找到的结果写入缓存
	 * 
	 * @param key
	 * @return
	 */
	public V get(String key) {
		V v = null;
		try {
			r_lock.lock();
			v = cache.get(key);
			if (v == null) {
				r_lock.unlock();// 一定要先释放锁 否则下面的读锁获取不了
				// TODO 去数据库里找 这里就不模拟了 直接赋值吧
				@SuppressWarnings("unchecked")
				V ret = (V) "result from database";
				System.out.println("日志记录：从数据库里查询到  key=" + key + "  value=" + ret + " 并放入缓存");
				if (null != ret) { // 将结果放入缓存
					// 获取写锁
					w_lock.lock();
					cache.put(key, ret);
					// 异常测试 放在这个位置后运行会抛异常 因为这里抛异常直接进入finally 没有释放写锁并重新上读锁
					// 所以在释放读锁时会跑出当前线程没有这个锁的异常
					// int i = 10 / testdata;
					w_lock.unlock();// 释放写锁
					// 因为finally需要释放锁 如果没有再次加上读锁 那么在finally里释放锁的时候将会抛异常
					r_lock.lock();
					@SuppressWarnings("unused")
					int i = 10 / testdata; // 一切正常
					return ret;
				}
			} else {
				System.out.println("日志记录：从缓存中获取到 key=" + key + " value=" + v);
			}
		} catch (Exception e) {
			System.out.println("抛异常了 " + e);
		} finally {
			r_lock.unlock();
		}
		return v;
	}

	public static void main(String[] args) {
		LockCache<String> cache = new LockCache<String>(0);
		// 第一次获取 缓存里还没有值
		cache.get("h");
		// 再次获取 缓存里应该有值了
		cache.get("h");
		
		cache.setTestData(1);
		cache.get("e");
	}
}
