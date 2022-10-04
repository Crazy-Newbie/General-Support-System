package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;

public class MenuListForm extends BaseForm {

	private static final String FORM_CODE = "MENU_LIST_FORM";

	public MenuListForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifierEnum(FormModifierEnum.READ);
	}
}
