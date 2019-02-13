package com.sportdec.application.exception;

public class TwitterAPIException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TwitterAPIException(String error) {
		super(error);
	}
	
	public TwitterAPIException(String error, Throwable cause) {
		super(error, cause);
	}
}
