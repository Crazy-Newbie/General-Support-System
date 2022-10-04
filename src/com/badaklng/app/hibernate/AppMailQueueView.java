package com.badaklng.app.hibernate;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppMailQueueView entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_MAIL_QUEUE_VIEW")

public class AppMailQueueView implements java.io.Serializable {

	// Fields

	private String mailId;
	private String status;
	private String errorMsg;
	private Integer maxAttempt;
	private Integer retry;
	private String mailTo;
	private String mailSubject;
	private String mailBody;
	private Timestamp submitDate;
	private Timestamp lastUpdate;
	private String mailCat;
	private String anyKey;
	private String mailCc;
	private String mailBcc;

	// Constructors

	/** default constructor */
	public AppMailQueueView() {
	}


	// Property accessors
	@Id

	@Column(name = "MAIL_ID", nullable = false, precision = 20, scale = 0)

	public String getMailId() {
		return this.mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	@Column(name = "STATUS", length = 1)

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ERROR_MSG", length = 512)

	public String getErrorMsg() {
		return this.errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Column(name = "MAX_ATTEMPT", precision = 2, scale = 0)

	public Integer getMaxAttempt() {
		return this.maxAttempt;
	}

	public void setMaxAttempt(Integer maxAttempt) {
		this.maxAttempt = maxAttempt;
	}

	@Column(name = "RETRY", precision = 2, scale = 0)

	public Integer getRetry() {
		return this.retry;
	}

	public void setRetry(Integer retry) {
		this.retry = retry;
	}

	@Column(name = "MAIL_TO", length = 1024)

	public String getMailTo() {
		return this.mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	@Column(name = "MAIL_SUBJECT", length = 512)

	public String getMailSubject() {
		return this.mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	@Column(name = "MAIL_BODY", length = 4000)

	public String getMailBody() {
		return this.mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	@Column(name = "SUBMIT_DATE", length = 7)

	public Timestamp getSubmitDate() {
		return this.submitDate;
	}

	public void setSubmitDate(Timestamp submitDate) {
		this.submitDate = submitDate;
	}

	@Column(name = "LAST_UPDATE", length = 7)

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Column(name = "MAIL_CAT", length = 30)

	public String getMailCat() {
		return this.mailCat;
	}

	public void setMailCat(String mailCat) {
		this.mailCat = mailCat;
	}

	@Column(name = "ANY_KEY", length = 75)

	public String getAnyKey() {
		return this.anyKey;
	}

	public void setAnyKey(String anyKey) {
		this.anyKey = anyKey;
	}

	@Column(name = "MAIL_CC", length = 1024)

	public String getMailCc() {
		return this.mailCc;
	}

	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}

	@Column(name = "MAIL_BCC", length = 1024)

	public String getMailBcc() {
		return this.mailBcc;
	}

	public void setMailBcc(String mailBcc) {
		this.mailBcc = mailBcc;
	}

}