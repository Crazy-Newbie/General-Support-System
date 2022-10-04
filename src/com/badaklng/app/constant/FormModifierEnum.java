package com.badaklng.app.constant;

public enum FormModifierEnum {
	NONE("NONE"), READ("READ"), CREATE("CREATE"), UPDATE("UPDATE"), DELETE("DELETE"), CANCEL("CANCEL"), ALL("ALL"),
	SEARCH("SEARCH"), SUBMIT("SUBMIT"), RETURN("RETURN"), GENERATE("GENERATE"), DOWNLOAD("DOWNLOAD"), BACK("BACK"),
	UNDO("UNDO"), UPLOAD("UPLOAD"), ADD("ADD"), REMOVE("REMOVE"), EXTRACT("EXTRACT"), CLEAN("CLEAN"), LOGIN("LOGIN"),
	SYNC("SYNC"), REFUND("REFUND"), DISABLE("DISABLE"), ENABLE("ENABLE"), PRINT("PRINT"), OPEN("OPEN"), CLOSE("CLOSE"),
	RESET("RESET"), ANSWER("ANSWER"), SAVE("SAVE"), SYSTEM("SYSTEM"), APPROVE("APPROVE"), REJECT("REJECT"), RUN("RUN"),
	COPY("COPY"), SWITCH("SWITCH");

	private String value;

	FormModifierEnum(String value) {
		this.value = value;
	}

	public String getFormModifierValue() {
		return value;
	}

	public int toLevel() {
		switch (this) {
		case ALL:
		case SYSTEM:
			return 90;
		case UNDO:
			return 60;
		case GENERATE:
			return 50;
		case DELETE:
		case REMOVE:
		case EXTRACT:
		case CLEAN:
		case REFUND:
		case RESET:
			return 40;
		case DOWNLOAD:
		case CANCEL:
		case DISABLE:
		case RUN:
		case ENABLE:
		case UPDATE:
		case APPROVE:
		case REJECT:
		case RETURN:
		case ADD:
		case SUBMIT:
		case OPEN:
		case CLOSE:
		case SWITCH:
			return 30;
		case COPY:
		case UPLOAD:
		case CREATE:
		case SAVE:
			return 20;
		case BACK:
		case ANSWER:
		case SEARCH:
		case READ:
		case PRINT:
		case LOGIN:
			return 10;
		case NONE:
		default:
			return 0;
		}
	}
}
