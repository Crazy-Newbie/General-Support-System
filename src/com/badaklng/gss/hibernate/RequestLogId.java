package com.badaklng.gss.hibernate;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RequestLogId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class RequestLogId implements java.io.Serializable {

	// Fields

	private Timestamp logDate;
	private String reqId;

	// Constructors

	/** default constructor */
	public RequestLogId() {
	}

	/** full constructor */
	public RequestLogId(Timestamp logDate, String reqId) {
		this.logDate = logDate;
		this.reqId = reqId;
	}

	// Property accessors

	@Column(name = "LOG_DATE", nullable = false, length = 7)

	public Timestamp getLogDate() {
		return this.logDate;
	}

	public void setLogDate(Timestamp logDate) {
		this.logDate = logDate;
	}

	@Column(name = "REQ_ID", nullable = false, length = 16)

	public String getReqId() {
		return this.reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RequestLogId))
			return false;
		RequestLogId castOther = (RequestLogId) other;

		return ((this.getLogDate() == castOther.getLogDate()) || (this.getLogDate() != null
				&& castOther.getLogDate() != null && this.getLogDate().equals(castOther.getLogDate())))
				&& ((this.getReqId() == castOther.getReqId()) || (this.getReqId() != null
						&& castOther.getReqId() != null && this.getReqId().equals(castOther.getReqId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getLogDate() == null ? 0 : this.getLogDate().hashCode());
		result = 37 * result + (getReqId() == null ? 0 : this.getReqId().hashCode());
		return result;
	}

}