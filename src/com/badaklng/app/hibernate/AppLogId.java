package com.badaklng.app.hibernate;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.badaklng.app.base.BaseModel;

/**
 * AppLogId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class AppLogId extends BaseModel implements java.io.Serializable {

	// Fields

	private String userId;
	private Timestamp logTime;

	// Constructors

	/** default constructor */
	public AppLogId() {
	}

	/** full constructor */
	public AppLogId(String userId, Timestamp logTime) {
		this.userId = userId;
		this.logTime = logTime;
	}

	// Property accessors

	@Column(name = "USER_ID", length = 30)

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "LOG_TIME", length = 7)

	public Timestamp getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AppLogId))
			return false;
		AppLogId castOther = (AppLogId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())))
				&& ((this.getLogTime() == castOther.getLogTime()) || (this.getLogTime() != null
						&& castOther.getLogTime() != null && this.getLogTime().equals(castOther.getLogTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result + (getLogTime() == null ? 0 : this.getLogTime().hashCode());
		return result;
	}

}