package com.Api.Exceptionhandler;

public class resourceNotFound extends Exception{

	@Override
	public String toString() {
		return "resourceNotFound [message=" + message + ", id=" + id + "]";
	}

	private String message;
	
	private int id;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public resourceNotFound(String message, int id) {
		super();
		this.message = message;
		this.id = id;
	}

	public resourceNotFound() {
		super();
		
	}

	public resourceNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public resourceNotFound(String message, Throwable cause) {
		super(message, cause);
		
	}

	public resourceNotFound(String message) {
		super(message);
		
	}

	public resourceNotFound(Throwable cause) {
		super(cause);
		
	}
	
	
	
}
