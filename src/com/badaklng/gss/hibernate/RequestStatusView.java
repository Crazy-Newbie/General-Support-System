package com.badaklng.gss.hibernate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.badaklng.app.utilities.Utility;

/**
 * RequestStatusView entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "REQUEST_STATUS_VIEW", schema = "GSS")

public class RequestStatusView implements java.io.Serializable {

	// Fields

	private RequestStatusViewId id;
	private String status;
	private String statusDesc;
	private String statusNote;
	private String updatedBy;

	// Constructors

	/** default constructor */
	public RequestStatusView() {
	}

	/** minimal constructor */
	public RequestStatusView(RequestStatusViewId id, String status, String updatedBy) {
		this.id = id;
		this.status = status;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public RequestStatusView(RequestStatusViewId id, String status, String statusDesc, String statusNote,
			String updatedBy) {
		this.id = id;
		this.status = status;
		this.statusDesc = statusDesc;
		this.statusNote = statusNote;
		this.updatedBy = updatedBy;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "logDate", column = @Column(name = "LOG_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "reqId", column = @Column(name = "REQ_ID", nullable = false, length = 16)) })

	public RequestStatusViewId getId() {
		return this.id;
	}

	public void setId(RequestStatusViewId id) {
		this.id = id;
	}

	@Column(name = "STATUS", nullable = false, length = 32)

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "STATUS_DESC")

	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	@Column(name = "STATUS_NOTE", length = 1000)

	public String getStatusNote() {
		return this.statusNote;
	}

	public void setStatusNote(String statusNote) {
		this.statusNote = statusNote;
	}

	@Column(name = "UPDATED_BY", nullable = false, length = 64)

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Transient
	public String getLogDateString() {
		return Utility.dateToDateString(getId().getLogDate());

	}

}