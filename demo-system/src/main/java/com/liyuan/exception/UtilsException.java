package com.liyuan.exception;

public class UtilsException extends RuntimeException {
	private static final long serialVersionUID = 524517497L;

	public UtilsException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public UtilsException(String s) {
		super(s);
	}
}