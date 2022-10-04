package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.utilities.Utility;

public class LoginasForm extends BaseForm {

	private static final String FORM_CODE = "LOGINAS_FORM";
	private String switchTo;

	public String getSwitchTo() {
		return switchTo;
	}

	public void setSwitchTo(String switchTo) {
		this.switchTo = Utility.decorateString(switchTo);
	}

	public LoginasForm() {
		reset();
	}

	public void reset() {
		super.reset();
		setFormCode(FORM_CODE);
		setSwitchTo("");
	}

}
