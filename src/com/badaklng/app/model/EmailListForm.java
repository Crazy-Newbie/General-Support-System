package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;

public class EmailListForm extends BaseForm {

	private static final String FORM_CODE = "EMAIL_LIST_FORM";

	private String mailTo;
	private String mailCc;
	private String mailBcc;
	private String mailSubject;
	private String mailStatus;
	private String mailCat;
	private String fromDate;
	private String toDate;

	public EmailListForm() {
		reset();
	}

	public void reset() {
		setFormCode(FORM_CODE);
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailCc() {
		return mailCc;
	}

	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}

	public String getMailBcc() {
		return mailBcc;
	}

	public void setMailBcc(String mailBcc) {
		this.mailBcc = mailBcc;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailStatus() {
		return mailStatus;
	}

	public void setMailStatus(String mailStatus) {
		this.mailStatus = mailStatus;
	}

	public String getMailCat() {
		return mailCat;
	}

	public void setMailCat(String mailCat) {
		this.mailCat = mailCat;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
