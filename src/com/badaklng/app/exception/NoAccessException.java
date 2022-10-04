package com.badaklng.app.exception;

@SuppressWarnings("serial")
public class NoAccessException extends ApplException {

	public NoAccessException(){
		
	}
	
	public NoAccessException(String message) {
		super(message);
	}

	public NoAccessException(String message, Throwable cause) {
		super(message, cause);
	}
}
