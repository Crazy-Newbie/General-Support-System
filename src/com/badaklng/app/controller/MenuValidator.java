package com.badaklng.app.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.model.MenuForm;
import com.badaklng.app.utilities.Utility;

public class MenuValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return MenuForm.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		if (arg0 instanceof MenuForm) {
			MenuForm form = (MenuForm) arg0;

			if (!form.getFormModifier().equals(FormModifierEnum.DELETE)) {
				if (form.getMenuId() != null) {
					if (form.getMenuId() == form.getMenuParentId())
						arg1.rejectValue("menuParentName", "Can't set menu parent to itself");
				}

				if (!Utility.isEmptyString(form.getFormId()) && Utility.isEmptyString(form.getFormName()))
					arg1.rejectValue("formName", "invalid.empty");

				if (Utility.isEmptyString(form.getFormId()) && !Utility.isEmptyString(form.getFormName()))
					arg1.rejectValue("formId", "invalid.empty");

				if (Utility.isEmptyString(form.getMenuName()))
					arg1.rejectValue("menuName", "invalid.empty");

				if (Utility.isEmptyString(form.getMenuUrl()))
					arg1.rejectValue("menuUrl", "invalid.empty");
			}
		}
	}

}
