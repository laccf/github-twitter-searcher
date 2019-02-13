package com.sportdec.application.exception;

public class FileNotFoundExpception extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileNotFoundExpception(String error) {
		super(error);
	}
	
	public FileNotFoundExpception(String error, Throwable cause) {
		super(error, cause);
	}
}
