package com.nagesh.apis.exceptions;

public class ApiException extends RuntimeException {
	
	private String message;

	public ApiException(String message) {
		super(message);
		this.message = message;
	}

	public ApiException() {
		super();
	} 
	
	
	

}
