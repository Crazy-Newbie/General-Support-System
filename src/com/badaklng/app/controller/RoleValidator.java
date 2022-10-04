package com.badaklng.app.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.badaklng.app.model.RoleForm;
import com.badaklng.app.utilities.Utility;

public class RoleValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return RoleForm.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		if (arg0 instanceof RoleForm) {
			RoleForm form = (RoleForm) arg0;

			if (Utility.isEmptyString(form.getRoleId()))
				arg1.rejectValue("roleId", "invalid.empty");

			if (Utility.isEmptyString(form.getRoleName()))
				arg1.rejectValue("roleName", "invalid.empty");
		}
	}

}
