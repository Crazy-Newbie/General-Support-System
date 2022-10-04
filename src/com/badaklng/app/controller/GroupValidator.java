package com.badaklng.app.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.model.GroupForm;
import com.badaklng.app.utilities.Utility;

public class GroupValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return GroupForm.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		if (arg0 instanceof GroupForm) {
			GroupForm form = (GroupForm) arg0;

			if (Utility.isEmptyString(form.getGroupId()))
				arg1.rejectValue("groupId", "invalid.empty");

			if (form.getFormModifier().equals(FormModifierEnum.ADD)
					|| form.getFormModifier().equals(FormModifierEnum.REMOVE)) {
				if (Utility.isEmptyString(form.getUserId()))
					arg1.rejectValue("userId", "invalid.empty");
			} else {
				if (Utility.isEmptyString(form.getGroupName()))
					arg1.rejectValue("groupName", "invalid.empty");
			}
		}
	}

}
