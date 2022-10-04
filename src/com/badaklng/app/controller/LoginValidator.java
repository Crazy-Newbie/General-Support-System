package com.badaklng.app.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.badaklng.app.model.LoginForm;
import com.badaklng.app.model.MessageEnum;
import com.badaklng.app.utilities.Utility;


@SuppressWarnings("rawtypes")
public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return LoginForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginForm frm = (LoginForm) target;

		if (Utility.isEmptyString(frm.getUsername())) {
			errors.rejectValue("username", MessageEnum.BDK001.toString());
		}

		if (Utility.isEmptyString(frm.getPassword())) {
			errors.rejectValue("password", MessageEnum.BDK002.toString());
		}

	}

}
