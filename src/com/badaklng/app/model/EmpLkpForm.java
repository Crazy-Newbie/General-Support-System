package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;

public class EmpLkpForm extends BaseForm {
	private static final String FORM_CODE = "EMP_LKP_FORM";

	public EmpLkpForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifier(FormModifierEnum.READ);
	}
}
