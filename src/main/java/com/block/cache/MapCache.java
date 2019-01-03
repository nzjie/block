package com.block.cache;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 以ConcurrentHashMap为存储介质的缓存，线程安全
 * 
 * @author mitnick
 *
 * @param <K>
 * @param <V>
 */
public class MapCache<K, V> implements Cache<K, V> {

	protected Map<K, V> map;
	/** 最后修改时间 */
	protected Date lastModify;

	/**
	 * 无参构造方法，初始化一个默认大小的、线程安全的Map
	 */
	public MapCache() {
		map = new ConcurrentHashMap<K, V>();
	}

	public V put(K key, V value) {
		V v = map.put(key, value);
		if (null == v || v != value) {
			updateModifyDate();
		}
		return v;
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		updateModifyDate();
		map.putAll(m);
	}

	public V remove(Object key) {
		return map.remove(key);
	}

	public V get(Object key) {
		return map.get(key);
	}

	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Date lastModifyDate() {
		return lastModify;
	}

	private void updateModifyDate() {
		lastModify = new Date();
	}

	public void clear() {
		map.clear();
	}

}
