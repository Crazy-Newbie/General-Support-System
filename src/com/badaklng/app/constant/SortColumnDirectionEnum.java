package com.badaklng.app.constant;

public enum SortColumnDirectionEnum {
	ASC("ASC"), DESC("DESC");

	private String value;

	SortColumnDirectionEnum(String value) {
		this.value = value;
	}

	public String getSortColumnDirectionValue() {
		return value;
	}

}
