package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;

public class UserListForm extends BaseForm {

	private static final String FORM_CODE = "USER_LIST_FORM";
	
	public UserListForm(){
		reset();
	}

	public void reset(){
		setFormCode(FORM_CODE);
	}
}
