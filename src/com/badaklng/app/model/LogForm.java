package com.badaklng.app.model;

import java.sql.Timestamp;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.utilities.Utility;

public class LogForm extends BaseForm {

	private static final String FORM_CODE = "LOG_FORM";
	private String startDate;
	private String endDate;

	public LogForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
		setFormModifier(FormModifierEnum.READ);
		setStartDate(Utility.getBeforeNow(Utility.DAY, 1, Utility.SHIFT_DATETIME_FORMAT));
		setEndDate(Utility.timestampToShiftDatetimeString(Utility.getNow()));
	}

	public String getStartDate() {
		return startDate;
	}

	public Timestamp getStartDateTimestamp() {
		return Utility.stringToTimestamp(startDate, "dd-MM-yyyy HH:mm");
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public Timestamp getEndDateTimestamp() {
		return Utility.stringToTimestamp(endDate, "dd-MM-yyyy HH:mm");
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
