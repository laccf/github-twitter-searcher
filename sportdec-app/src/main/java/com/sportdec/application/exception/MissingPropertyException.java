package com.sportdec.application.exception;

public class MissingPropertyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MissingPropertyException(String error) {
		super(error);
	}
	
	public MissingPropertyException(String error, Throwable cause) {
		super(error, cause);
	}
}
