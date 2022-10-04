package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;

public class UserLkpForm extends BaseForm {
	private static final String FORM_CODE = "USER_LKP_FORM";

	public UserLkpForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifier(FormModifierEnum.READ);
	}
}
