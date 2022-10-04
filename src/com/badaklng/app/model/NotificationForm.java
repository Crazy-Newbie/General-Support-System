package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;

public class NotificationForm extends BaseForm {

	private static final String FORM_CODE = "NOTIFICATION_FORM";

	private String notifId;

	public NotificationForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
	}

	public String getNotifId() {
		return notifId;
	}

	public void setNotifId(String notifId) {
		this.notifId = notifId;
	}

}
