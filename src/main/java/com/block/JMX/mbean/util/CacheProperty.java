package com.block.JMX.mbean.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.slf4j.Logger;

import com.block.JMX.mbean.BaseMBeanListener;
import com.block.JMX.mbean.MBeanListener;
import com.block.cache.Cache;
import com.block.cache.CacheFactory;

/**
 * 从缓存中读取给定的key对应的value,如果缓存中存在，则返回，如果没有，<br>
 * 则从配置文件中读取 如果配置文件有更改，则需要重新将配置文件加载进来
 * 
 * @author mitnick
 *
 */
public class CacheProperty implements CachePropertyMBean {
	public static final Logger _Logger = org.slf4j.LoggerFactory
			.getLogger(CacheProperty.class);

	// TODO
	/** 是否定时从配置文件中加载内容进入缓存 */
	public final static int REGULAR_UPDATE_MARK = 1 << 0;
	// TODO
	/** 是否不需要更新，即使配置文件发生了变化 */
	public final static int STOP_UPDATE_MARK = 1 << 1;
	// TODO
	/** 不从缓存里读取，直接从配置文件中读取，注意，每次读取都会重新load配置文件进来 */
	public final static int READ_RROM_PROPERTY = 1 << 2;

	int mark;

	/** 配置文件路径 */
	protected String path;

	/** 配置文件 */
	protected Properties properties;

	protected Cache<Object, Object> cache = CacheFactory.getCache();

	private Object cacheLoce = new Object();

	/** MBean注册监听 */
	MBeanListener mBeanListener = null;

	/**
	 * 根据给定路径构造缓存配置
	 * 
	 * @param path
	 */
	public CacheProperty(String path) {
		Properties prop = null;
		try {
			prop = loadProperty(path);
		} catch (PropertyException e) {
			_Logger.error("配置文件不存在 path=" + path + " " + e);
		}
		this.path = path;
		this.properties = prop;
		try {
			ObjectName name = new ObjectName("CacheProperties:name="
					+ getClass().getSimpleName());
			// 注册jmx管理
			if (null == mBeanListener) {
				mBeanListener = new BaseMBeanListener();
			}
			mBeanListener.registerMBean(this, name);
		} catch (MalformedObjectNameException e) {
			_Logger.error("ObjectName  invaid " + getClass().getSimpleName());
		}
	}

	/**
	 * 根据给定文件构造缓存配置
	 * 
	 * @param path
	 */
	public CacheProperty(File file) throws FileNotFoundException {
		this(null == file ? "" : file.getAbsolutePath());
	}

	/**
	 * 根据给定的key返回对应的值
	 * 
	 * @param key
	 * @return
	 */

	public String getProperty(String key) {
		String value = (String) cache.get(key);
		if (null == value) {
			_Logger.info("没有从缓存中读取到数据，尝试将配置文件重新加载");
			reload();
		}
		return (String) cache.get(key);
	}

	/**
	 * 直接从配置文件中读取
	 * 
	 * @param key
	 * @return
	 */
	public String getPropertyFromFile(String key) {
		String property = properties.getProperty(key);
		return property;
	}

	/**
	 * 重新加载配置文件
	 * 
	 * @param path
	 * @return
	 */
	public void reload() {
		Properties prop = null;
		try {
			prop = loadProperty(this.path);
		} catch (PropertyException e) {
			_Logger.error("重新加载配置失败 " + e);
		}
		properties = prop;
	}

	/**
	 * 读取配置文件
	 * 
	 * @param path
	 * @return
	 * @throws PropertyException
	 */
	public Properties loadProperty(String path) throws PropertyException {
		/*
		 * File f = new File(path); String[] list = f.list(); if (f.exists()) {
		 * for (String string : list) { System.out.println(string); } } else {
		 * System.exit(-1); }
		 */

		InputStream is = null;
		Properties prop = new Properties();
		try {
			is = new BufferedInputStream(new FileInputStream(path));
			prop.load(is);
			Set<String> keySet = prop.stringPropertyNames();
			// 将配置项读入缓存
			for (String key : keySet) {
				cache.put(key, prop.getProperty(key));
			}
		} catch (FileNotFoundException e) {
			throw new PropertyException("文件不存在", e);
		} catch (IOException e) {
			throw new PropertyException("文件不存在", e);
		} finally {
			if (null != is)
				try {
					is.close();
				} catch (IOException e) {
					// 忽略
				}
		}
		return prop;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void updateProperty() {
		synchronized (cacheLoce) {
			cache.clear();
			_Logger.info("缓存已被清空");
		}
	}

	public String get(String key) {
		if (null == key)
			return null;
		return getProperty(key);
	}

}
