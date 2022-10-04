package com.badaklng.gss.model;

import java.sql.Timestamp;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.gss.hibernate.Request;
import com.badaklng.gss.hibernate.Team;

public class RequestForm extends BaseForm {
	
	private static final String FORM_CODE = "REQUEST_FORM";
	private String reqId;
	private String teamId;
	private String reqBy;
	private String reqDesc;
	private String location;
	
	
	public RequestForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifierEnum(FormModifierEnum.CREATE);
		setReqId("");
		teamId = "";
		reqBy = "";
		setReqDesc("");
		location = "";
	}
	
	public void setData(Request r) {
		
		if(r.getTeam()!=null) {
			this.teamId = r.getTeam().getTeamId();
		}
		
		this.reqId = r.getReqId();
//		this.teamId = r.getTeam().getTeamId().toString();
		this.reqBy = r.getReqBy();
		this.reqDesc = r.getReqDesc();
		this.location = r.getLocation();
	}
	
	
//	public String getReqId() {
//		return reqId;
//	}
//	
//	public void setReqId(String reqId) {
//		this.reqId = reqId;
//	}
	
	public String getTeamId() {
		return teamId;
	}
	
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	
	public String getReqBy() {
		return reqBy;
	}
	
	public void setReqBy(String reqBy) {
		this.reqBy = reqBy;
	}
	
//	public String getReqDesc() {
//		return reqDesc;
//	}
//	
//	public void setReqDesc(String reqDesc) {
//		this.reqBy = reqDesc;
//	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public String getReqDesc() {
		return reqDesc;
	}

	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	
}
