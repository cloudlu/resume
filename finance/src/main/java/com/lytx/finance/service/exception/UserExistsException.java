package com.lytx.finance.service.exception;

public class UserExistsException extends ExistsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6788471991801793979L;

	public UserExistsException() {
		// TODO Auto-generated constructor stub
	}

	public UserExistsException(final String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserExistsException(final Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UserExistsException(final String message, final Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	
}
