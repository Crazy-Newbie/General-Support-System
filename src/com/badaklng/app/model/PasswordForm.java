package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;

public class PasswordForm extends BaseForm {

	private static final String FORM_CODE = "PASSWORD_FORM";

	private String userId;

	private String employeeId;

	private String oldPass;

	private String newPass;

	private String newPassConf;

	public PasswordForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifier(FormModifierEnum.CREATE);
		userId = "";
		employeeId = "";
		oldPass = "";
		newPass = "";
		newPassConf = "";
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getNewPassConf() {
		return newPassConf;
	}

	public void setNewPassConf(String newPassConf) {
		this.newPassConf = newPassConf;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
