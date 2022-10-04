package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;

public class CompLkpForm extends BaseForm {

	private static final String FORM_CODE = "LOOKUP_FORM";
		
	public CompLkpForm(){
		reset();
	}
	
	public void reset(){
		super.reset();
		setFormCode(FORM_CODE);
	}
	
}
