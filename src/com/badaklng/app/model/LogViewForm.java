package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.utilities.Utility;

public class LogViewForm extends BaseForm {

	private static final String FORM_CODE = "LOG_VIEW_FORM";

	private String locationId;
	private String locationName;
	private String date;
	private String timeStart;
	private String timeEnd;
	private String zone;

	private String dateStart;
	private String dateEnd;

	private Integer zone1;
	private Integer zone2;
	
	private Boolean emgcOpen;

	public LogViewForm() {
		reset();
	}

	public void reset() {
		super.reset();
		setFormCode(FORM_CODE);
		timeStart = "00:00";
		timeEnd = "23:59";
		dateStart = Utility.getNow("dd-MM-yyyy HH:mm:ss");
		dateEnd = dateStart;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getZone1() {
		return zone1;
	}

	public void setZone1(Integer zone1) {
		this.zone1 = zone1;
	}

	public Integer getZone2() {
		return zone2;
	}

	public void setZone2(Integer zone2) {
		this.zone2 = zone2;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Boolean getEmgcOpen() {
		return emgcOpen;
	}

	public void setEmgcOpen(Boolean emgcOpen) {
		this.emgcOpen = emgcOpen;
	}
	
	
}
