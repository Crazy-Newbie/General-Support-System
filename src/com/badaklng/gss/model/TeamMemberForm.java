package com.badaklng.gss.model;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;

public class TeamMemberForm extends BaseForm {

	private static final String FORM_CODE = "TEAM_MEMBER_FORM";

	private String teamId;
	private String memberId;

	public TeamMemberForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifierEnum(FormModifierEnum.SEARCH);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

}
