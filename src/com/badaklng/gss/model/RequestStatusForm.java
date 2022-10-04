package com.badaklng.gss.model;

import java.sql.Timestamp;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;

public class RequestStatusForm extends BaseForm{
	private static final String FORM_CODE = "REQUEST_STATUS_FORM";

	private String status;
    private String statusDesc;
    private String statusNote;
    private String updatedBy;
    private String reqId;
    
    private String startDate;
	private String endDate;
	
	
	public RequestStatusForm () {
		reset();
	}
	
	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifierEnum(FormModifierEnum.CREATE);
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusNote() {
		return statusNote;
	}

	public void setStatusNote(String statusNote) {
		this.statusNote = statusNote;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


}
