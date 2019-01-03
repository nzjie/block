package com.block.JMX.mbean.util;

public class PropertyException extends Exception {

	private static final long serialVersionUID = 1L;

	public PropertyException() {

	}

	public PropertyException(String message) {
		super(message);
	}

	public PropertyException(String message, Throwable e) {
		super(message, e);
	}

}
