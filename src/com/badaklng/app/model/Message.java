package com.badaklng.app.model;

import com.badaklng.app.base.BaseModel;
import com.badaklng.app.constant.MessageTypeEnum;

public class Message extends BaseModel {
	protected String message;
	protected MessageTypeEnum messageType;
	protected Boolean dismissible;

	public Message() {
		this("", MessageTypeEnum.INFO);
	}

	public Message(MessageTypeEnum MessageTypeEnum, String message) {
		super();
		this.message = message;
		this.messageType = MessageTypeEnum;
		this.dismissible = true;
	}

	public Message(MessageTypeEnum MessageTypeEnum, String message, Boolean dismissible) {
		super();
		this.message = message;
		this.messageType = MessageTypeEnum;
		this.dismissible = dismissible;
	}

//	public Message(String message) {
//		this(message, MessageTypeEnum.INFO);
//	}
//
//	public Message(MessageTypeEnum messageType) {
//		this("", messageType);
//	}

	public Message(String message, MessageTypeEnum messageType) {
		super();
		this.message = message;
		this.messageType = messageType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageTypeEnum getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageTypeEnum messageType) {
		this.messageType = messageType;
	}

	public String getType() {
		return messageType.getCSSAlertValue();
	}

	public String getMessageDescription() {
		return message;
	}

	public Boolean getDismissible() {
		return dismissible;
	}

	public void setDismissible(Boolean dismissible) {
		this.dismissible = dismissible;
	}

}
