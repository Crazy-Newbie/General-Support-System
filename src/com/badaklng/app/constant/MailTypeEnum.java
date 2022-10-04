package com.badaklng.app.constant;

public enum MailTypeEnum {
	PUBLIC("PUBLIC_MAIL"), SYSTEM_ERROR("SYS_ERR_NOT"), SYSTEM_NOTIF("SYS_NOTIF");

	private String value;

	@Override
	public String toString() {
		return this.value;
	}

	MailTypeEnum(String value) {
		this.value = value;
	}

	public String getMailTypeValue() {
		return value;
	}

}
