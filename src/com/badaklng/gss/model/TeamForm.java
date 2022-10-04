package com.badaklng.gss.model;

import java.sql.Timestamp;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.gss.hibernate.Team;

public class TeamForm extends BaseForm {
	private static final String FORM_CODE = "TEAM_FORM";
	private String teamId;
	private String teamName;
	private String createdBy;
	private Timestamp createdDate;
	private String updatedBy;
	private Timestamp updatedDate;
	
	
	public TeamForm() {
		reset();
	}
	
	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifierEnum(FormModifierEnum.CREATE);
		teamId = "";
		teamName = "";
	}
	

	public void setData(Team team) {
		this.teamId = team.getTeamId();
		this.teamName = team.getTeamName();
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	
	
}
