package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;

public class RoleListForm extends BaseForm {

	private static final String FORM_CODE = "ROLE_LIST_FORM";
	
	public RoleListForm(){
		reset();
	}
	
	public void reset(){
		setFormCode(FORM_CODE);
		setFormModifier(FormModifierEnum.READ);
	}
}
