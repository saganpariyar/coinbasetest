package com.coinbase.utils;

public class DataValidationException extends Exception {

	/**
	 * 
	 */
	String msg;
	String desc;

	private static final long serialVersionUID = 1L;

	public DataValidationException(String msg) {
		this.msg = msg;
	}

	public DataValidationException(String msg, String desc) {
		this.msg = msg;
		this.desc = desc;
	}

	public String getMessage() {
		return this.msg;
	}

	public String getDesc() {
		return this.desc;
	}

}
