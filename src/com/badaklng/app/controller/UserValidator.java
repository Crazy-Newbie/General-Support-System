package com.badaklng.app.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.model.UserForm;
import com.badaklng.app.utilities.Utility;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return UserForm.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		if (arg0 instanceof UserForm) {
			UserForm form = (UserForm) arg0;

			if (form.getFormModifierEnum().equals(FormModifierEnum.CREATE)) {
				if (!form.getLdapFlag())
					if (Utility.isEmptyString(form.getPwd()))
						arg1.rejectValue("pwd", "invalid.empty");
			}

			if (Utility.isEmptyString(form.getUserId()))
				arg1.rejectValue("userId", "invalid.empty");

			if (Utility.isEmptyString(form.getName()))
				arg1.rejectValue("name", "invalid.empty");

		}
	}

}
