package com.badaklng.app.exception;

@SuppressWarnings("serial")
public class ApplException extends Exception {
	public ApplException() {

	}

	public ApplException(String message) {
		super(message);
	}

	public ApplException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
