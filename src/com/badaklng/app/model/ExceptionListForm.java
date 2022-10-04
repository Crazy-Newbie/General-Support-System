package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;

public class ExceptionListForm extends BaseForm {
	
	private static final String FORM_CODE = "EXCEPTION_LIST_FORM";
	
	public ExceptionListForm(){
		reset();
	}
	
	public void reset(){
		setFormCode(FORM_CODE);
		setFormModifier(FormModifierEnum.READ);
	}

}
