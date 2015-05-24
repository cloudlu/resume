package com.lytx.finance.service.exception;

public class NotExistsException extends Exception {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -5330312022973711384L;

	public NotExistsException() {
	}

	public NotExistsException(final String message) {
		super(message);
	}

	public NotExistsException(final Throwable cause) {
		super(cause);
	}

	public NotExistsException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
}
