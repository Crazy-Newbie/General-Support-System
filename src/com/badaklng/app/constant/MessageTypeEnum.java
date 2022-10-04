package com.badaklng.app.constant;

public enum MessageTypeEnum {
	INFO("INFO"), WARNING("WARNING"), DANGER("DANGER"), SUCCESS("SUCCESS"), PRIMARY("PRIMARY");

	private String value;

	MessageTypeEnum(String value) {
		this.value = value;
	}

	public String getMessageTypeValue() {
		return value;
	}

	public String getCSSAlertValue() {
		switch (this) {

		case SUCCESS:
			return "alert-success";
		case WARNING:
			return "alert-warning";
		case DANGER:
			return "alert-danger";
		case PRIMARY:
			return "alert-primary";
		default:
			return "alert-info";
		}
	}

	public String getCSSTextStyle() {
		switch (this) {

		case SUCCESS:
			return "text-success";
		case INFO:
			return "text-info";
		case WARNING:
			return "text-warning";
		case DANGER:
			return "text-danger";
		case PRIMARY:
			return "text-primary";
		default:
			return "text-info";
		}
	}

	public String getCSSAlertIcon() {
		switch (this) {

		case SUCCESS:
			return "fas fa-fw fa-check";
		case INFO:
			return "fas fa-fw fa-exclamation-circle";
		case WARNING:
			return "fas fa-fw fa-exclamation-triangle";
		case DANGER:
			return "far fa-fw fa-times-circle";
		case PRIMARY:
			return "fas fa-fw fa-info-circle";
		default:
			return "far fa-fw fa-bell";
		}
	}

}
