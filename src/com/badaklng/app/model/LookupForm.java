package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;

public class LookupForm extends BaseForm {

	private static final String FORM_CODE = "LOOKUP_FORM";
		
	public LookupForm(){
		reset();
	}
	
	public void reset(){
		super.reset();
		setFormCode(FORM_CODE);
	}
	
}
