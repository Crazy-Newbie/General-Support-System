package com.badaklng.app.model;

//https://technet.microsoft.com/en-us/library/ee309278(office.12).aspx

public enum MIMEEnum {
	DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
	DOC("application/vnd.ms-word"),
	XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
	XLS("application/vnd.ms-excel"),
	PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
	PPT("application/vnd.ms-powerpoint"),
	PDF("application/pdf"),
	CSV("text/csv");
	
	private final String MIMEType;
	
	MIMEEnum(String value) {
		this.MIMEType = value;
	}
	
	public String getMIMEType(String key){
		return MIMEType;
	}
}
