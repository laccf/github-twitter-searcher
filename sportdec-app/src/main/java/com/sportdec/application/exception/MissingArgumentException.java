package com.sportdec.application.exception;

public class MissingArgumentException  extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MissingArgumentException(String error) {
		super(error);
	}
	
	public MissingArgumentException(String error, Throwable cause) {
		super(error, cause);
	}
}
