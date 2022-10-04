package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;

public class FormForm extends BaseForm {
	private static final String FORM_CODE = "FORM_FORM";
	private String formNo;
	private String formDesc;
	
	public FormForm(){
		reset();
	}
	
	public FormForm(String formNo, String formDesc) {
		reset();
		this.formNo = formNo;
		this.formDesc = formDesc;
	}

	public void reset(){
		setFormCode(FORM_CODE);
		setFormModifier(FormModifierEnum.CREATE);
		formNo = "";
		formDesc = "";
	}
	public String getFormNo() {
		return formNo;
	}

	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}

	public String getFormDesc() {
		return formDesc;
	}

	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
	}

}
