package com.badaklng.gss.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.badaklng.app.utilities.Utility;
import com.badaklng.gss.model.TeamForm;

public class TeamValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return TeamForm.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		if (arg0 instanceof TeamForm) {
			TeamForm form = (TeamForm) arg0;

			if (Utility.isEmptyString(form.getTeamId()))
				arg1.rejectValue("teamId", "invalid.empty");

			if (Utility.isEmptyString(form.getTeamName()))
				arg1.rejectValue("teamName", "invalid.empty");
		}
	}

}
