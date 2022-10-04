package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.utilities.Utility;

public class LoginForm extends BaseForm {

	private static final String FORM_CODE = "LOGIN_FORM";

	private String username;
	private String password;
	private String redirect;

	public LoginForm() {
		reset();
	}

	public void reset() {
		super.reset();
		setFormCode(FORM_CODE);
		setUsername("");
		setPassword("");
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String str) {
		password = str;
	}

	public void setUsername(String str) {
		username = Utility.decorateString(str).toUpperCase();
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

}
