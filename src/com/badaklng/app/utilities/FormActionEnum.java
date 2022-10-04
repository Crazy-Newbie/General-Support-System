package com.badaklng.app.utilities;

public enum FormActionEnum {
	NONE("NONE"), LOGIN("LOGIN"), SEARCH("SEARCH"), CREATE("CREATE"), DELETE("DELETE"), 
	SAVE("SAVE"), UPDATE("UPDATE"), READ("READ"), SWITCH("SWITCH"), DOWNLOAD("DOWNLOAD");

	private String value;

	FormActionEnum(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}

}
