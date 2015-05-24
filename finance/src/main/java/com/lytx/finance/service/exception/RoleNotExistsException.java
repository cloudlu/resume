package com.lytx.finance.service.exception;

public class RoleNotExistsException extends NotExistsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4488749770682407070L;

	public RoleNotExistsException() {
	}

	public RoleNotExistsException(final String message) {
		super(message);
	}

	public RoleNotExistsException(final Throwable cause) {
		super(cause);
	}

	public RoleNotExistsException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
}
