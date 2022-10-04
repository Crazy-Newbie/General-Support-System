package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;
public class DashboardForm extends BaseForm {

	private static final String FORM_CODE = "DASHBOARD_FORM";

	// for dashboard
	// for switch user
	private Integer locationId;
	private String ipAddress;

	public DashboardForm() {
		reset();
	}

	public void reset() {
		super.reset();
		setFormCode(FORM_CODE);
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	

}
