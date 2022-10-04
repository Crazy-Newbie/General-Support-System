package com.badaklng.gss.model;

import java.sql.Timestamp;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;

public class TeamListForm extends BaseForm {
	
	private static final String FORM_CODE = "TEAM_LIST_FORM";

	private String teamId;
    private String teamName;
    private String createdBy;
    private Timestamp createdDate;
    private String updatedBy;
    private Timestamp updatedDate;
    private Double teamMember;
    private Double neww;
    private Double asg;
    private Double pnd;
    private Double cmpl;
    private Double canc;
    private Double nos;

	public TeamListForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifierEnum(FormModifierEnum.SEARCH);
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

	public Double getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(Double teamMember) {
		this.teamMember = teamMember;
	}

	public Double getNeww() {
		return neww;
	}

	public void setNeww(Double neww) {
		this.neww = neww;
	}

	public Double getAsg() {
		return asg;
	}

	public void setAsg(Double asg) {
		this.asg = asg;
	}

	public Double getPnd() {
		return pnd;
	}

	public void setPnd(Double pnd) {
		this.pnd = pnd;
	}

	public Double getCmpl() {
		return cmpl;
	}

	public void setCmpl(Double cmpl) {
		this.cmpl = cmpl;
	}

	public Double getCanc() {
		return canc;
	}

	public void setCanc(Double canc) {
		this.canc = canc;
	}

	public Double getNos() {
		return nos;
	}

	public void setNos(Double nos) {
		this.nos = nos;
	}
	
	
}
