package com.badaklng.gss.hibernate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * RequestLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "REQUEST_LOG", schema = "GSS")

public class RequestLog implements java.io.Serializable {

	// Fields

	private RequestLogId id;
	private Request request;
	private String status;
	private String statusNote;
	private String updatedBy;

	// Constructors

	/** default constructor */
	public RequestLog() {
	}

	/** minimal constructor */
	public RequestLog(RequestLogId id, Request request, String status, String updatedBy) {
		this.id = id;
		this.request = request;
		this.status = status;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public RequestLog(RequestLogId id, Request request, String status, String statusNote, String updatedBy) {
		this.id = id;
		this.request = request;
		this.status = status;
		this.statusNote = statusNote;
		this.updatedBy = updatedBy;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "logDate", column = @Column(name = "LOG_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "reqId", column = @Column(name = "REQ_ID", nullable = false, length = 16)) })

	public RequestLogId getId() {
		return this.id;
	}

	public void setId(RequestLogId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REQ_ID", nullable = false, insertable = false, updatable = false)

	public Request getRequest() {
		return this.request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	@Column(name = "STATUS", nullable = false, length = 32)

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}