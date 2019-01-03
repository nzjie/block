package com.block.base;

import java.util.List;

/**
 * 方法使用泛型 类名的定义不一定要使用泛型 但是一定要有<XXX>声明
 * 
 * @author ajie
 *
 */
@SuppressWarnings("unused")
public class MethodFanxing {

	public <T extends List<Object>> T getMapper(T t) {
		return t;
	}

	public <T> void getType() {

	}

	public static void main(String[] args) {
		MethodFanxing mf = new MethodFanxing();
		/*String ret = mf.getMapper(new String("sadf"));
		System.out.println(ret);*/
	}

}
