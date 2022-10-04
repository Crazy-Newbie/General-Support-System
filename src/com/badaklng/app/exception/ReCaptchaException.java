package com.badaklng.app.exception;

@SuppressWarnings("serial")
public class ReCaptchaException extends Exception {

	public ReCaptchaException() {

	}

	public ReCaptchaException(String message) {
		super(message);
	}

	public ReCaptchaException(String message, Throwable cause) {
		super(message, cause);
	}
}
