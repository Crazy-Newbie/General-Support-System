package com.badaklng.app.model;

public class SMSResponse {
	private String messageId;
	private String status;
	private String message;
	private String appsKey;

	public SMSResponse() {
		this("", "", "");
	}

	public SMSResponse(String messageId, String status, String message) {
		super();
		this.messageId = messageId;
		this.status = status;
		this.message = message;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAppsKey() {
		return appsKey;
	}

	public void setAppsKey(String appsKey) {
		this.appsKey = appsKey;
	}

}
