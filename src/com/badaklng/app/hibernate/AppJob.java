package com.badaklng.app.hibernate;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.badaklng.app.base.BaseModel;

/**
 * AppJob entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_JOB")

public class AppJob extends BaseModel implements java.io.Serializable {

	// Fields

	private String jobId;
	private String jobName;
	private String syntax;
	private Boolean isActive;
	private String createBy;
	private Timestamp createDate;
	private String jobDesc;
	private String scheduledTime;

	// Constructors

	/** default constructor */
	public AppJob() {
	}

	/** minimal constructor */
	public AppJob(String jobId, String jobName, String syntax, Boolean isActive, String createBy, Timestamp createDate,
		String scheduledTime) {
		this.jobId = jobId;
		this.jobName = jobName;
		this.syntax = syntax;
		this.isActive = isActive;
		this.createBy = createBy;
		this.createDate = createDate;
		this.scheduledTime = scheduledTime;
	}

	// Property accessors
	@Id

	@Column(name = "JOB_ID", unique = true, nullable = false, length = 12)

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@Column(name = "JOB_NAME", nullable = false, length = 32)

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Column(name = "SYNTAX", nullable = false, length = 128)

	public String getSyntax() {
		return this.syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	@Column(name = "IS_ACTIVE", nullable = false, precision = 1, scale = 0)

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "CREATE_BY", nullable = false, length = 6)

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "CREATE_DATE", nullable = false, length = 7)

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "JOB_DESC", length = 128)

	public String getJobDesc() {
		return this.jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	@Column(name = "SCHEDULED_TIME")
	public String getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

}