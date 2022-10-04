package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;

public class JobsListForm extends BaseForm {

	private static final String FORM_CODE = "JOBS_LIST_FORM";
	
	private String startDate;
	private String endDate;

	public JobsListForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
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
