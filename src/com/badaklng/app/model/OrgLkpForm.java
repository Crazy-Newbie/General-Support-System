package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;

public class OrgLkpForm extends BaseForm {
	private static final String FORM_CODE = "ORG_LKP_FORM";

	public OrgLkpForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifier(FormModifierEnum.READ);
	}
}
