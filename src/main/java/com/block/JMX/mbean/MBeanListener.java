package com.block.JMX.mbean;

import javax.management.ObjectInstance;
import javax.management.ObjectName;

public interface MBeanListener {

	ObjectInstance registerMBean(Object object, ObjectName name);
}
