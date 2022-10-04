package com.badaklng.app.hibernate;

import java.sql.Timestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.badaklng.app.base.BaseModel;
import com.badaklng.app.utilities.Utility;

/**
 * AppLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_LOG")

public class AppLog extends BaseModel implements java.io.Serializable {

	// Fields

	private AppLogId id;
	private String ipAddress;
	private Timestamp logDate;
	private String logType;
	private String arguments;
	private String formCode;
	private String formAction;

	// Constructors

	/** default constructor */
	public AppLog() {
	}

	/** minimal constructor */
	public AppLog(AppLogId id) {
		this.id = id;
	}

	/** full constructor */
	public AppLog(AppLogId id, String ipAddress, Timestamp logDate, String logType, String arguments, String formCode,
			String formAction) {
		this.id = id;
		this.ipAddress = ipAddress;
		this.logDate = logDate;
		this.logType = logType;
		this.arguments = arguments;
		this.formCode = formCode;
		this.formAction = formAction;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "userId", column = @Column(name = "USER_ID", length = 30) ),
			@AttributeOverride(name = "logTime", column = @Column(name = "LOG_TIME", length = 7) ) })

	public AppLogId getId() {
		return this.id;
	}

	public void setId(AppLogId id) {
		this.id = id;
	}

	@Column(name = "IP_ADDRESS", length = 30)

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name = "LOG_DATE", length = 7)

	public Timestamp getLogDate() {
		return this.logDate;
	}

	public void setLogDate(Timestamp logDate) {
		this.logDate = logDate;
	}

	@Column(name = "LOG_TYPE", length = 75)

	public String getLogType() {
		return this.logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	@Column(name = "ARGUMENTS", length = 1024)

	public String getArguments() {
		return this.arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	@Column(name = "FORM_CODE", length = 20)

	public String getFormCode() {
		return this.formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	@Column(name = "FORM_ACTION", length = 20)

	public String getFormAction() {
		return this.formAction;
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	@Transient
	public String getLogTimeString() {
		return Utility.timestampToShiftDatetimeString(id.getLogTime());
	}

}