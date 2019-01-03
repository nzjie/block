package com.block.JMX.mbean;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import org.slf4j.Logger;

public class BaseMBeanListener implements MBeanListener {
	public static final Logger _Logger = org.slf4j.LoggerFactory
			.getLogger(BaseMBeanListener.class);

	public ObjectInstance registerMBean(Object object, ObjectName name) {
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		try {
			ObjectInstance instance = mBeanServer.registerMBean(object, name);
			_Logger.info("MBean注册成功" + object + "  " + name.toString());
			return instance;
		} catch (InstanceAlreadyExistsException e) {
			_Logger.error("MBean注册失败" + name + " 失败原因：" + e);
		} catch (MBeanRegistrationException e) {
			_Logger.error("MBean注册失败" + name + " 失败原因：" + e);
		} catch (NotCompliantMBeanException e) {
			_Logger.error("MBean注册失败" + name + " 失败原因：" + e);
		}
		return null;
	}

}
