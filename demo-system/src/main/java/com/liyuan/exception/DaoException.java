package com.liyuan.exception;

import org.springframework.dao.DataAccessException;

public class DaoException extends DataAccessException {
	private static final long serialVersionUID = -812180847L;

	public DaoException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DaoException(String s) {
		super(s);
	}
}