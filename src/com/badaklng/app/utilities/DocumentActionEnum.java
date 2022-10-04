package com.badaklng.app.utilities;

public enum DocumentActionEnum {
	NONE("NONE"), UPLOAD("UPLOAD"), DOWNLOAD("DOWNLOAD"), DELETE("DELETE"), ALL(
			"ALL");

	private String value;

	DocumentActionEnum(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}

	public int toLevel() {
		switch (this) {
		case DELETE:
			return 30;
		case UPLOAD:
			return 20;
		case DOWNLOAD:
			return 10;
		case NONE:
			return 0;
		case ALL:
			return 90;
		default:
			return 0;
		}
	}
}
