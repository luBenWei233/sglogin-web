package org.sg.loginweb.exception;

public class GlobalException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private String exception;

	public GlobalException(String exception) {
		this.exception = exception;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
}
